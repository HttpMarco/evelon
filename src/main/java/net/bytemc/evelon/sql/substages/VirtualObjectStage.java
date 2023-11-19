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
public final class VirtualObjectStage extends AbstractSubElementStage<Object> {

    @Override
    public boolean isElement(Class<?> type) {
        // This stage is the last stage. This can only be a collection of other stages. Always true
        return true;
    }

    @Override
    public void onParentTableCollectData(List<String> queries, String table, RepositoryClass<?> current, @Nullable Field field, ForeignKey... keys) {
        // the collected object statements;
        var rowValues = SQLForeignKeyHelper.convertToDatabaseElementsWithType(keys);
        for (var row : current.getRows()) {
            var stage = transformStage(row.getType());
            // create net repository class, because there can be multiple rows of the same type
            var subTableRepositoryClazz = new RepositoryClass<>(row.getType());
            if (stage instanceof SQLElementStage<?> elementStage) {
                // collect all current rows
                var result = elementStage.anonymousElementRowData(row, subTableRepositoryClazz);
                rowValues.add(TableCreationProcess.appendPrimaryKey(row, SQLHelper.getRowName(row) + " " + result));
            } else if (stage instanceof SubElementStage<?> subElementStage) {
                var subTableName = table + "_" + SQLHelper.getRowName(row);
                subElementStage.onParentTableCollectData(queries, subTableName, subTableRepositoryClazz, row, current.collectForeignKeyValues(table));
            }
        }
        // add database schema link
        SQLForeignKeyHelper.convertToDatabaseForeignLink(rowValues, keys);
        queries.add(SQLHelper.create(table, String.join(", ", rowValues)));
    }

    @Override
    public List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<Object> clazz, Object value, ForeignKey... keys) {
        var queries = new ArrayList<String>();
        var values = SQLForeignKeyHelper.convertKeyObjectsToElements(keys);

        for (var row : clazz.getRows()) {
            var originalStage = getStageHandler().getElementStage(row.getType());
            var stage = transform(originalStage);
            var object = Reflections.readField(value, row);
            var objectClass = new RepositoryClass<>(row.getType());

            if(originalStage instanceof SQLElementStageTransformer transformer) {
                // we need to roll back the value to the original object type
                object = transformer.transform(object);
            }

            if (stage instanceof SubElementStage<?> subElementStage) {
                queries.addAll(subElementStage.onAnonymousParentElement(parent.appendChildrenName(SQLHelper.getRowName(row)), row, parent, objectClass, object, clazz.collectForeignKeyValues(value)));
            } else if (stage instanceof SQLElementStage<?> elementStage) {
                var result = elementStage.anonymousElementEntryData(objectClass, row, object);
                values.put(result.left(), result.right());
            }
        }
        queries.add(SQLHelper.insertDefault(table, String.join(", ", values.keySet()), String.join(", ", values.values())));
        return queries;
    }

    @Override
    public List<String> onUpdateParentElement(String table, Repository<?> parent, RepositoryQuery<Object> query, RepositoryClass<Object> clazz, Object value, ForeignKey... keys) {
        var queries = new ArrayList<String>();
        var values = SQLForeignKeyHelper.convertKeyObjectsToElements(keys);

        for (var row : clazz.getRows()) {
            var originalStage = getStageHandler().getElementStage(row.getType());
            var stage = transform(originalStage);
            var object = Reflections.readField(value, row);
            var objectClass = new RepositoryClass<>(row.getType());

            if(originalStage instanceof SQLElementStageTransformer transformer) {
                // we need to roll back the value to the original object type
                object = transformer.transform(object);
            }

            if (stage instanceof SQLElementStage<?> elementStage) {
                var result = elementStage.anonymousElementEntryData(objectClass, row, object);
                values.put(result.left(), result.right());
            } else if (stage instanceof SubElementStage<?>) {
                var repositoryClass = new RepositoryClass<>(row.getType());
                var subTable = table + "_" + SQLHelper.getRowName(row);
                queries.addAll(this.onAnonymousUpdateParentElement(subTable, parent, query, repositoryClass, object, repositoryClass.collectForeignKeyValues(value)));
            }
        }
        if (!values.isEmpty()) {
            queries.add(SQLHelper.update(table, (String.join(", ", values.keySet().stream().map(it -> it + "=" + values.get(it)).toList()) + SQLHelper.getDatabaseFilterQuery(query.getFilters()))));
        }
        return queries;
    }

    @Override
    public Object createInstance(String table, Field parentField, RepositoryClass<Object> clazz, SQLResultSet result) {
        var object = Reflections.allocate(clazz.clazz());
        for (var row : clazz.getRows()) {
            var originalStage = getStageHandler().getElementStage(row.getType());
            var stage = transform(originalStage);

            var subRepositoryClazz = new RepositoryClass(row.getType());
            var rowName = SQLHelper.getRowName(row);
            Object value;
            if (stage instanceof SubElementStage<?> subElementStage) {
                var subTableName = table + "_" + rowName;
                value = subElementStage.createInstance(subTableName, row, subRepositoryClazz, result);
            } else if (stage instanceof SQLElementStage<?> elementStage) {
                value = elementStage.anonymousCreateObject(subRepositoryClazz, rowName, result.getTable(table));
            } else {
                throw new UnsupportedOperationException("Cannot create instance of " + row.getType().getSimpleName() + " for " + clazz.clazz().getSimpleName());
            }

            if(originalStage instanceof SQLElementStageTransformer transformer) {
                // we need to transform the value to the executed object type
                value = transformer.rollback(value, row);
            }
            Reflections.writeField(object, row, value);
        }
        return object;
    }

    private <T> Stage<T> transformStage(Class<T> row) {
        return transform(getStageHandler().getElementStage(row));
    }

    private <T> Stage<T> transform(Stage<T> stage) {
        if(stage instanceof SQLElementStageTransformer transformer) {
            stage = transformer.transformTo();
            if (stage instanceof SQLElementStageTransformer<?>) {
                throw new IllegalStateException("Transformer can't be a transformer");
            }
        }
        return stage;
    }
}
