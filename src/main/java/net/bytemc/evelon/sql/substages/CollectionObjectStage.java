/*
 * Copyright 2019-2023 ByteMC team & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.exception.StageNotSupportedException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;

import java.lang.reflect.Field;
import java.util.*;

public final class CollectionObjectStage implements SubElementStage<Collection<?>> {

    private static final String VALUE_NAME = "_value";

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    @Override
    public void onParentTableCollectData(List<String> queries, String table, RepositoryClass<?> current, Field field, ForeignKey... keys) {
        var listType = Reflections.readGenericFromClass(field)[0];
        var stage = StageHandler.getInstance().getElementStage(listType);
        var rowValues = new ArrayList<String>();
        // collect all needed foreign keys
        SQLForeignKeyHelper.convertToDatabaseElementsWithType(rowValues, keys);

        var rowName = SQLHelper.getRowName(field);
        if (stage instanceof SQLElementStage<?> elementStage) {
            rowValues.add(rowName + VALUE_NAME + " " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(listType)));
        } else if (stage instanceof SubElementStage<?> subElementStage && subElementStage instanceof VirtualObjectStage) {
            var rowClazz = new RepositoryClass<>(listType);
            for (var row : rowClazz.getRows()) {
                var rowStage = StageHandler.getInstance().getElementStage(row.getType());
                if (rowStage instanceof SQLElementStage<?> elementStage) {
                    rowValues.add(SQLHelper.getRowName(row) + VALUE_NAME + " " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(row.getType())));
                } else {
                    throw new StageNotSupportedException(row.getType());
                }
            }
        } else {
            throw new StageNotSupportedException(listType);
        }
        // add database schema link
        SQLForeignKeyHelper.convertToDatabaseForeignLink(rowValues, keys);
        queries.add(SQLHelper.create(table, String.join(", ", rowValues)));
    }

    @Override
    public List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<Collection<?>> clazz, Collection<?> value, ForeignKey... keys) {
        var queries = new ArrayList<String>();
        var listType = Reflections.readGenericFromClass(field)[0];
        var stage = StageHandler.getInstance().getElementStage(listType);

        RepositoryClass<?> listRepository = new RepositoryClass<>(listType);
        if (stage instanceof SQLElementStage<?> elementStage) {
            if (value == null) {
                return queries;
            }
            for (var item : value) {
                var columns = SQLForeignKeyHelper.convertKeyObjectsToElements(keys);
                var element = elementStage.anonymousElementEntryData(listRepository, null, item);
                columns.put(SQLHelper.getRowName(field) + "_" + element.left(), element.right());
                queries.add(SQLHelper.insertDefault(table, String.join(", ", columns.keySet()), String.join(", ", columns.values())));
            }
        } else if (stage instanceof SubElementStage<?> subElementStage && subElementStage instanceof VirtualObjectStage) {
            for (var element : value) {
                var columns = SQLForeignKeyHelper.convertKeyObjectsToElements(keys);
                for (var row : listRepository.getRows()) {
                    var rowStage = StageHandler.getInstance().getElementStage(row.getType());
                    if (rowStage instanceof SQLElementStage<?> elementStage) {
                        var object = Reflections.readField(element, row);
                        columns.put(SQLHelper.getRowName(row) + VALUE_NAME, elementStage.anonymousElementEntryData(new RepositoryClass<>(row.getType()), row, object).right());
                    } else {
                        throw new StageNotSupportedException(row.getType());
                    }
                }
                queries.add(SQLHelper.insertDefault(table, String.join(", ", columns.keySet()), String.join(", ", columns.values())));
            }

        } else {
            throw new StageNotSupportedException(listType);
        }
        return queries;
    }

    @Override
    public List<String> onUpdateParentElement(String table, Repository<?> parent, RepositoryQuery<Collection<?>> query, RepositoryClass<Collection<?>> clazz, Collection<?> value, ForeignKey... keys) {


        //TODO
        return null;
    }

    @Override
    public Collection<?> createInstance(String tableName, Field parentField, RepositoryClass<Collection<?>> clazz, SQLResultSet resultSet) {

        var listType = Reflections.readGenericFromClass(parentField)[0];

        // we can't use the result, because we need to collect all elements
        return SQLConnection.executeQuery("SELECT * FROM " + tableName + ";", (result) -> {
            var list = new ArrayList<>();
            var stage = StageHandler.getInstance().getElementStage(listType);
            while (result.next()) {
                var databaseResultSet = new SQLResultSet();
                var repositoryClass = new RepositoryClass<>(listType);
                var table = databaseResultSet.addTable("default");

                if (stage instanceof SQLElementStage<?> elementStage) {
                    var rowName = SQLHelper.getRowName(parentField) + VALUE_NAME;
                    table.setProperty(rowName, result.getObject(rowName));
                    list.add(elementStage.anonymousCreateObject(repositoryClass, rowName, table));
                } else if (stage instanceof SubElementStage<?> subElementStage && subElementStage instanceof VirtualObjectStage) {
                    var object = Reflections.allocate(listType);

                    for (var row : repositoryClass.getRows()) {
                        var rowStage = StageHandler.getInstance().getElementStage(row.getType());
                        if (rowStage instanceof SQLElementStage<?> elementStage) {
                            var rowName = SQLHelper.getRowName(row) + VALUE_NAME;
                            table.setProperty(SQLHelper.getRowName(row), result.getObject(rowName));
                            Reflections.writeField(object, row, elementStage.anonymousCreateObject(new RepositoryClass<>(row.getType()), SQLHelper.getRowName(row), table));
                        } else {
                            throw new StageNotSupportedException(row.getType());
                        }
                    }
                    list.add(object);
                } else {
                    throw new StageNotSupportedException(listType);
                }
            }
            return list;
        }, new ArrayList<>());
    }
}
