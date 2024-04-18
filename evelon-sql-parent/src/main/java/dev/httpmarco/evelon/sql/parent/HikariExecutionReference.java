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

    public void stack(String query, Object[] parameters) {
        this.sqlQueries.put(query, parameters);
    }

    public void stack(String query) {
        this.sqlQueries.put(query, new Object[0]);
    }

    public void append(@NotNull HikariExecutionReference reference) {
        this.sqlQueries.putAll(reference.sqlQueries());
    }
}
