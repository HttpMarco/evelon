package net.bytemc.evelon.sql;

import java.util.HashMap;
import java.util.Map;

public final class DatabaseResultSet {

    private final Map<String, Object> properties = new HashMap<>();

    public Object getProperty(String id) {
        return this.properties.get(id);
    }
}
