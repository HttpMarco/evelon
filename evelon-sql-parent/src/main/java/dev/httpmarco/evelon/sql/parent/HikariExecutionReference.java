package dev.httpmarco.evelon.sql.parent;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

@Getter
@Accessors(fluent = true)
public final class HikariExecutionReference {

    private final HashMap<String, Object[]> sqlQueries = new LinkedHashMap<>();

    public void stack(String query, Object... parameters) {
        this.sqlQueries.put(query, parameters);
    }

    public void append(@NotNull HikariExecutionReference reference) {
        this.sqlQueries.putAll(reference.sqlQueries());
    }
}
