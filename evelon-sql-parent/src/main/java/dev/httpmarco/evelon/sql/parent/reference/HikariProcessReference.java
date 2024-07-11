package dev.httpmarco.evelon.sql.parent.reference;

import dev.httpmarco.evelon.process.ProcessReference;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionFunction;
import lombok.Getter;
import lombok.experimental.Accessors;
import java.sql.ResultSet;
import java.util.*;
import java.util.function.Function;

@Getter
@Accessors(fluent = true)
public final class HikariProcessReference extends ProcessReference<HikariConnection> {

    private final List<HikariReferenceData<ResultSet>> sqlQueries = new ArrayList<>();

    public HikariProcessReference(HikariConnection connection) {
        super(connection);
    }

    public <R> R directly(String query, Object[] arguments, Function<ResultSet, R> consumer) {
        return this.connection().query(query, arguments, consumer);
    }

    @Deprecated
    public HikariProcessReference append(String query, Object[] parameters, HikariConnectionFunction<ResultSet> consumer) {
        this.sqlQueries.add(new HikariReferenceData<>(query, parameters, consumer));
        return this;
    }

    @Deprecated
    public HikariProcessReference append(String query, HikariConnectionFunction<ResultSet> consumer) {
        return this.append(query, new Object[0], consumer);
    }

    @Deprecated
    public HikariProcessReference append(String query, Object[] parameters) {
        return this.append(query, parameters, resultSet -> {
        });
    }

    @Deprecated
    public HikariProcessReference append(String query) {
        return this.append(query, new Object[0]);
    }

}
