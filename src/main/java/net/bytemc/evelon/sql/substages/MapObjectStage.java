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

import net.bytemc.evelon.exception.stage.StageNotSupportedException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MapObjectStage extends AbstractSubElementStage<Map<?, ?>> {

    @Override
    public boolean isElement(Class<?> type) {
        return Map.class.isAssignableFrom(type);
    }

    @Override
    public void onParentTableCollectData(List<String> queries, String table, RepositoryClass<?> current, Field field, ForeignKey... keys) {
        var generic = Reflections.readGenericFromClass(field);
        var keyType = generic[0];
        var valueType = generic[1];
        var keyStage = getStageHandler().getElementStage(keyType);
        var valueStage = getStageHandler().getElementStage(valueType);

        if (!(keyStage instanceof SQLElementStage<?> keyElementStage)) {
            throw new StageNotSupportedException(keyType);
        }
        var rowValues = SQLForeignKeyHelper.convertToDatabaseElementsWithType(keys);

        // add map key value -> unique -> primary key
        var keyDatabaseType = keyElementStage.anonymousElementRowData(field, new RepositoryClass<>(keyType));
        if (keyDatabaseType.equals(SQLType.TEXT.toString())) {
            keyDatabaseType = SQLType.VARCHAR.toString().formatted(255);
        }
        rowValues.add(SQLHelper.getRowName(field) + "_key " + keyDatabaseType + " PRIMARY KEY");

        if (valueStage instanceof SQLElementStage<?> elementStage) {
            rowValues.add(SQLHelper.getRowName(field) + VALUE_ID + " " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(keyType)));
        } else {
            //todo
            throw new StageNotSupportedException(valueType);
        }
        // add database schema link
        SQLForeignKeyHelper.convertToDatabaseForeignLink(rowValues, keys);
        queries.add(SQLHelper.create(table, String.join(", ", rowValues)));
    }

    @Override
    public List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<Map<?, ?>> clazz, Map<?, ?> value, ForeignKey... keys) {
        //todo: check duplicated code
        var queries = new ArrayList<String>();
        var generic = Reflections.readGenericFromClass(field);
        var keyType = generic[0];
        var valueType = generic[1];
        var keyStage = getStageHandler().getElementStage(keyType);
        var valueStage = getStageHandler().getElementStage(valueType);

        for (var keyObject : value.keySet()) {
            var elements = SQLForeignKeyHelper.convertKeyObjectsToElements(keys);
            if (keyStage instanceof SQLElementStage<?> elementStage) {
                elements.put(SQLHelper.getRowName(field) + "_key", elementStage.anonymousElementEntryData(new RepositoryClass<>(keyType), null, keyObject).right());
            }
            if (valueStage instanceof SQLElementStage<?> elementStage) {
                elements.put(SQLHelper.getRowName(field) + VALUE_ID, elementStage.anonymousElementEntryData(new RepositoryClass<>(valueType), null, value.get(keyObject)).right());
            }
            queries.add(SQLHelper.insertDefault(table, String.join(", ", elements.keySet()), String.join(", ", elements.values())));
        }
        return queries;
    }

    @Override
    public List<String> onUpdateParentElement(String table, Field field, Repository<?> parent, RepositoryQuery<Map<?, ?>> query, RepositoryClass<Map<?, ?>> clazz, Map<?, ?> value, ForeignKey... keys) {
        //todo
        return null;
    }

    @Override
    public Map<?, ?> createInstance(String tableName, @Nullable Field parentField, RepositoryClass<Map<?, ?>> clazz, SQLResultSet resultSet) {

        var map = new HashMap<>();
        var generic = Reflections.readGenericFromClass(parentField);

        var keyType = generic[0];
        var valueType = generic[1];
        var keyStage = getStageHandler().getElementStage(keyType);
        var valueStage = getStageHandler().getElementStage(valueType);

        return SQLConnection.executeQuery("SELECT * FROM " + tableName, result -> {
            while (result.next()) {
                if (keyStage instanceof SQLElementStage<?> keyElementStage) {
                    if (valueStage instanceof SQLElementStage<?> valueElementStage) {
                        var databaseResultSet = new SQLResultSet();
                        var table = databaseResultSet.addTable("default");
                        var keyName = SQLHelper.getRowName(parentField) + "_key";
                        var rowName = SQLHelper.getRowName(parentField) + VALUE_ID;
                        table.setProperty(rowName, result.getObject(rowName));
                        table.setProperty(keyName, result.getObject(keyName));
                        map.put(keyElementStage.anonymousCreateObject(new RepositoryClass<>(keyType), keyName, table), valueElementStage.anonymousCreateObject(new RepositoryClass<>(valueType), rowName, table));
                    }
                } else {
                    throw new StageNotSupportedException(keyType);
                }
            }
            return map;
        }, map);
    }
}
