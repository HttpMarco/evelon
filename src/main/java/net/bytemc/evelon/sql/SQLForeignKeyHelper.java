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

package net.bytemc.evelon.sql;
import net.bytemc.evelon.repository.RepositoryClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SQLForeignKeyHelper {

    public static void convertToDatabaseElementsWithType(List<String> elements, ForeignKey... keys) {
        for (var key : keys) {
            var keyStage = StageHandler.getInstance().getElementStage(key.getForeignKey().getType());
            if (keyStage instanceof SQLElementStage<?> elementStage) {
                elements.add(SQLHelper.getRowName(key.getForeignKey()) + " " + elementStage.anonymousElementRowData(key.getForeignKey(), new RepositoryClass<>(key.getForeignKey().getType())) + " NOT NULL");
            }
        }
    }

    public static void convertToDatabaseForeignLink(List<String> elements, ForeignKey... keys) {
        for (var key : keys) {
            elements.add("FOREIGN KEY (" + key.parentField() + ") REFERENCES " + key.getForeignKey() + "(" + key.parentField() + ") ON DELETE CASCADE");
        }
    }

    public static Map<String, String> convertKeyObjectsToElements(ForeignKey... keys) {
        var elements = new HashMap<String, String>();
        for (var key : keys) {
            elements.put(key.parentField(), key.toString());
        }
        return elements;
    }

}
