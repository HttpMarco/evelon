package dev.httpmarco.evelon.sql.parent.reference;

import dev.httpmarco.evelon.process.ProcessReference;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionFunction;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionType;
import dev.httpmarco.evelon.sql.parent.connection.HikariStatementBuilder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.ResultSet;
import java.util.*;

@Getter
@Accessors(fluent = true)
public final class HikariProcessReference extends ProcessReference<HikariConnection> {

    private final List<HikariReferenceData<ResultSet>> sqlQueries = new ArrayList<>();

    public HikariProcessReference(HikariConnection connection) {
        super(connection);
    }

    public <R> R directly(HikariConnectionType type, String query, Object[] arguments, HikariStatementBuilder<R> consumer) {
        return this.connection().query(type, query, arguments, consumer);
    }

    @Deprecated
    public HikariProcessReference append(String query, Object[] parameters, HikariConnectionFunction<ResultSet> consumer) {
        this.sqlQueries.add(new HikariReferenceData<>(query, parameters, consumer));
        return this;
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
