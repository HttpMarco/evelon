package dev.httpmarco.evelon.sql.parent.reference;

import dev.httpmarco.evelon.process.ProcessReference;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionFunction;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.*;

@Getter
@Accessors(fluent = true)
public final class HikariProcessReference implements ProcessReference<HikariProcessReference> {

    private final List<HikariReferenceData<ResultSet>> sqlQueries = new ArrayList<>();

    public HikariProcessReference append(String query, Object[] parameters, HikariConnectionFunction<ResultSet> consumer) {
        this.sqlQueries.add(new HikariReferenceData<>(query, parameters, consumer));
        return this;
    }

    public HikariProcessReference append(String query, HikariConnectionFunction<ResultSet> consumer) {
        return this.append(query, new Object[0], consumer);
    }

    public HikariProcessReference append(String query, Object[] parameters) {
        return this.append(query, parameters, resultSet -> {});
    }

    public HikariProcessReference append(String query) {
        return this.append(query, new Object[0]);
    }

    @Override
    public void bind(@NotNull HikariProcessReference reference) {
        this.sqlQueries.addAll(reference.sqlQueries());
    }
}
