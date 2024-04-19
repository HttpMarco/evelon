package dev.httpmarco.evelon.sql.parent;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Getter
@Accessors(fluent = true)
public final class HikariExecutionReference {

    private final HashMap<String, Object[]> sqlQueries = new LinkedHashMap<>();

    public HikariExecutionReference bind(String query, Object[] parameters) {
        this.sqlQueries.put(query, parameters);
        return this;
    }

    public HikariExecutionReference bind(String query) {
        return this.bind(query, new Object[0]);
    }

    public void append(@NotNull HikariExecutionReference reference) {
        this.sqlQueries.putAll(reference.sqlQueries());
    }
}
