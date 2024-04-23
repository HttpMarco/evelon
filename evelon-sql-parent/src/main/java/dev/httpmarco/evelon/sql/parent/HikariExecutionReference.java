package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.osgan.utils.data.Pair;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@Accessors(fluent = true)
public final class HikariExecutionReference {

    private final List<Pair<String, Object[]>> sqlQueries = new ArrayList<>();

    public HikariExecutionReference bind(String query, Object[] parameters) {
        this.sqlQueries.add(new Pair<>(query, parameters));
        return this;
    }

    public HikariExecutionReference bind(String query) {
        return this.bind(query, new Object[0]);
    }

    public void append(@NotNull HikariExecutionReference reference) {
        this.sqlQueries.addAll(reference.sqlQueries());
    }
}
