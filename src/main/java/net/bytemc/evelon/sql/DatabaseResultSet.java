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

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public final class DatabaseResultSet {

    private final Map<String, Table> tables = new HashMap<>();

    public Table addTable(String id) {
        var table = new Table(id);
        this.tables.put(id, table);
        return table;
    }

    public Table getTable(String id) {
        return this.tables.get(id);
    }

    @Getter
    @RequiredArgsConstructor
    public class Table {

        private final String id;
        private final Map<String, Object> properties = new HashMap<>();

        public Object get(String key) {
            return this.properties.get(key);
        }

        public <T> T get(String key, Class<T> clazz) {
            return (T) this.properties.get(key);
        }

        public void setProperty(String id, Object o) {
            this.properties.put(id, o);
        }
    }
}
