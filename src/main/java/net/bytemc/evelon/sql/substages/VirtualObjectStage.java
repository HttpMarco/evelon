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
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.process.TableCreationProcess;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

/**
 * This stage is only for collections of other stages as declared fields.
 */
public final class VirtualObjectStage implements SubElementStage<Object> {

    @Override
    public boolean isElement(Class<?> type) {
        // This stage is the last stage. This can only be a collection of other stages. Always true
        return true;
    }

    @Override
    public void onParentTableCollectData(List<String> queries, String table, RepositoryClass<?> current, @Nullable Field field, ForeignKey... keys) {
        // the collected object statements;
        var rowValues = new ArrayList<String>();
        // collect all needed foreign keys
        SQLForeignKeyHelper.convertToDatabaseElementsWithType(rowValues, keys);
        for (var row : current.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());
            if (stage instanceof SQLElementStage<?> elementStage) {
                // create net repository class, because there can be multiple rows of the same type
                var elementClass = new RepositoryClass<>(row.getType());
                // collect all current rows
                var result = elementStage.anonymousElementRowData(row, elementClass);
                rowValues.add(TableCreationProcess.appendPrimaryKey(row, SQLHelper.getRowName(row) + " " + result));
            } else if (stage instanceof SubElementStage<?> subElementStage) {
                var subTableName = table + "_" + SQLHelper.getRowName(row);
                var subTableRepositoryClazz = new RepositoryClass<>(row.getType());

                //TODO use current.collect
                subElementStage.onParentTableCollectData(queries, subTableName, subTableRepositoryClazz, row, current.getPrimaries().stream().map(it -> new ForeignKey(table, it)).toArray(ForeignKey[]::new));
            } else if(stage instanceof SQLElementStageTransformer transformer) {
                //todo
            }
        }
        // add database schema link
        SQLForeignKeyHelper.convertToDatabaseForeignLink(rowValues, keys);
        queries.add(SQLHelper.create(table, String.join(", ", rowValues)));
    }

    @Override
    public List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<Object> clazz, Object value, ForeignKeyObject... keys) {
        var queries = new ArrayList<String>();
        var values = SQLForeignKeyHelper.convertKeyObjectsToElements(keys);

        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());
            if (stage == null) {
                throw new StageNotFoundException(row.getType());
            }
            var object = Reflections.readField(value, row);
            var objectClass = new RepositoryClass<>(row.getType());
            if (stage instanceof SubElementStage<?> subElementStage) {
                queries.addAll(subElementStage.onAnonymousParentElement(parent.appendChildrenName(SQLHelper.getRowName(row)), row, parent, objectClass, object, clazz.collectForeignKeyValues(value)));
            } else if (stage instanceof SQLElementStage<?> elementStage) {
                var result = elementStage.anonymousElementEntryData(objectClass, row, object);
                values.put(result.left(), result.right());
            } else if (stage instanceof SQLElementStageTransformer transformer) {
                //todo
            }
        }
        queries.add(SQLHelper.insertDefault(table, String.join(", ", values.keySet()), String.join(", ", values.values())));
        return queries;
    }

    @Override
    public List<String> onUpdateParentElement(String table, Repository<?> parent, RepositoryQuery<Object> query, RepositoryClass<Object> clazz, Object value, ForeignKeyObject... keys) {
        var queries = new ArrayList<String>();
        var values = SQLForeignKeyHelper.convertKeyObjectsToElements(keys);

        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());
            if (stage == null) {
                throw new StageNotFoundException(row.getType());
            }
            var object = Reflections.readField(value, row);
            var objectClass = new RepositoryClass<>(row.getType());

            if (stage instanceof SQLElementStage<?> elementStage) {
                var result = elementStage.anonymousElementEntryData(objectClass, row, object);
                values.put(result.left(), result.right());
                continue;
            }

            if (stage instanceof SubElementStage<?>) {
                var repositoryClass = new RepositoryClass<>(row.getType());
                var subTable = table + "_" + SQLHelper.getRowName(row);
                queries.addAll(this.onAnonymousUpdateParentElement(subTable, parent, query, repositoryClass, object, repositoryClass.collectForeignKeyValues(value)));
            }

        }
        if(!values.isEmpty()) {
            queries.add(SQLHelper.update(table, (String.join(", ", values.keySet().stream().map(it -> it + "=" + values.get(it)).toList()) + SQLHelper.getDatabaseFilterQuery(query.getFilters()))));
        }
        return queries;
    }

    @Override
    public Object createInstance(String table, Field parentField, RepositoryClass<Object> clazz, SQLResultSet SQLResultSet) {
        var object = Reflections.allocate(clazz.clazz());
        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());
            if (stage == null) {
                throw new StageNotFoundException(row.getType());
            }
            if (stage instanceof SubElementStage<?> subElementStage) {
                Reflections.writeField(object, row, subElementStage.createInstance(table + "_" + SQLHelper.getRowName(row), row, new RepositoryClass(row.getType()), SQLResultSet));
            } else if (stage instanceof SQLElementStage<?> elementStage) {
                Reflections.writeField(object, row, elementStage.anonymousCreateObject(new RepositoryClass<>(row.getType()), SQLHelper.getRowName(row), SQLResultSet.getTable(table)));
            } else if (stage instanceof SQLElementStageTransformer transformer) {
                var transformedStage = transformer.transformTo();
                //todo
            }
        }
        return object;
    }
}
