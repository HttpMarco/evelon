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
        DatabaseForeignKeyHelper.convertToDatabaseElementsWithType(rowValues, keys);
        for (var row : current.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());
            if (stage instanceof ElementStage<?> elementStage) {
                // create net repository class, because there can be multiple rows of the same type
                var elementClass = new RepositoryClass<>(row.getType());
                // collect all current rows
                var result = elementStage.anonymousElementRowData(row, elementClass);
                rowValues.add(TableCreationProcess.appendPrimaryKey(row, DatabaseHelper.getRowName(row) + " " + result));
            } else if (stage instanceof SubElementStage<?> subElementStage) {
                subElementStage.onParentTableCollectData(queries, table + "_" + DatabaseHelper.getRowName(row), new RepositoryClass<>(row.getType()), row, current.getPrimaries().stream().map(it -> new ForeignKey(table, it)).toArray(ForeignKey[]::new));
            }
        }
        // add database schema link
        DatabaseForeignKeyHelper.convertToDatabaseForeignLink(rowValues, keys);
        queries.add(DatabaseHelper.create(table, String.join(", ", rowValues)));
    }

    @Override
    public List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<Object> clazz, Object value, ForeignKeyObject... keys) {
        var queries = new ArrayList<String>();
        var values = DatabaseForeignKeyHelper.convertKeyObjectsToElements(keys);;
        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());
            if (stage == null) {
                throw new StageNotFoundException(row.getType());
            }
            var object = Reflections.readField(value, row);
            var objectClass = new RepositoryClass<>(row.getType());
            if (stage instanceof SubElementStage<?> subElementStage) {
                queries.addAll(subElementStage.onAnonymousParentElement(parent.appendChildrenName(DatabaseHelper.getRowName(row)), row, parent, objectClass, object, clazz.collectForeignKeyValues(value)));
            } else if (stage instanceof ElementStage<?> elementStage) {
                var result = elementStage.anonymousElementEntryData(objectClass, row, object);
                values.put(result.left(), result.right());
            } else if(stage instanceof ElementStageTransformer transformer) {
                //todo
            }
        }
        queries.add(DatabaseHelper.insertDefault(table, String.join(", ", values.keySet()), String.join(", ", values.values())));
        return queries;
    }

    @Override
    public Object createInstance(String table, Field parentField, RepositoryClass<Object> clazz, DatabaseResultSet databaseResultSet) {
        var object = Reflections.allocate(clazz.clazz());
        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());
            if (stage == null) {
                throw new StageNotFoundException(row.getType());
            }
            if (stage instanceof SubElementStage<?> subElementStage) {
                Reflections.writeField(object, row, subElementStage.createInstance(table + "_" + DatabaseHelper.getRowName(row), row, new RepositoryClass(row.getType()), databaseResultSet));
            } else if (stage instanceof ElementStage<?> elementStage) {
                Reflections.writeField(object, row, elementStage.anonymousCreateObject(new RepositoryClass<>(row.getType()), DatabaseHelper.getRowName(row), databaseResultSet.getTable(table)));
            } else if (stage instanceof ElementStageTransformer transformer) {
                var transformedStage = transformer.transformTo();
                //todo
            }
        }
        return object;
    }
}
