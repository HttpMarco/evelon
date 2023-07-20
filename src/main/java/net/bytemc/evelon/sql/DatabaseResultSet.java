package net.bytemc.evelon.sql;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public final class DatabaseResultSet {

    private Map<String, Table> tables = new HashMap<>();

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

        public void setProperty(String id, Object o) {
            this.properties.put(id, o);
        }
    }
}
