package dev.httpmarco.evelon.sql.parent.reference;

import dev.httpmarco.evelon.process.ProcessReference;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.ResultSet;
import java.util.*;
import java.util.function.Consumer;

@Getter
@Accessors(fluent = true)
public final class HikariExecutionProcessReference implements ProcessReference<HikariExecutionProcessReference> {

    private final List<HikariReferenceData<ResultSet>> sqlQueries = new ArrayList<>();

    public HikariExecutionProcessReference append(String query, Object[] parameters, Consumer<ResultSet> consumer) {
        this.sqlQueries.add(new HikariReferenceData<>(query, parameters, consumer));
        return this;
    }

    public HikariExecutionProcessReference append(String query, Object[] parameters) {
        return this.append(query, parameters, resultSet -> {});
    }

    public HikariExecutionProcessReference append(String query) {
        return this.append(query, new Object[0]);
    }

    @Override
    public void bind(HikariExecutionProcessReference reference) {
        this.sqlQueries.addAll(reference.sqlQueries());
    }
}
