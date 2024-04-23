package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.common.Triple;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.*;
import java.util.function.Consumer;

@Getter
@Accessors(fluent = true)
public final class HikariExecutionReference {

    private final List<Triple<String, Object[], Consumer<ResultSet>>> sqlQueries = new ArrayList<>();

    public HikariExecutionReference bind(String query, Object[] parameters) {
        return this.bind(query, parameters, resultSet -> {});
    }

    public HikariExecutionReference bind(String query, Object[] parameters, Consumer<ResultSet> consumer) {
        this.sqlQueries.add(new Triple<>(query, parameters, consumer));
        return this;
    }

    public HikariExecutionReference bind(String query) {
        return this.bind(query, new Object[0]);
    }

    public void append(@NotNull HikariExecutionReference reference) {
        this.sqlQueries.addAll(reference.sqlQueries());
    }
}
