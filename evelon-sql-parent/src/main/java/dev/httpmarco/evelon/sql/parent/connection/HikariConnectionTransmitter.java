package dev.httpmarco.evelon.sql.parent.connection;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.query.response.QueryResponse;
import dev.httpmarco.evelon.common.query.response.ResponseType;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public final class HikariConnectionTransmitter {

    private HikariConnection hikariConnection;

    public QueryResponse executeUpdate(final String query, List<Object> arguments) {
        return this.transferPreparedStatement(PreparedStatement::executeUpdate, query, arguments.toArray());
    }

    public QueryResponse executeUpdate(final String query, Object... arguments) {
        return this.transferPreparedStatement(PreparedStatement::executeUpdate, query, arguments);
    }


    public QueryResponse executeQuery(final String query, Object... arguments) {
        return this.transferPreparedStatement(PreparedStatement::executeQuery, query, arguments);
    }

    public QueryResponse transferPreparedStatement(StatementTransmitter statementTransmitter, String query, Object @NotNull ... arguments) {
        var response = QueryResponse.empty();
        Evelon.LOGGER.info("Executing query: {}", query);
        try (var connection = hikariConnection.getConnection(); var statement = connection.prepareStatement(query)) {
            for (int i = 0; i < arguments.length; i++) {
                statement.setString(i + 1, Objects.toString(arguments[i]));
            }
            statementTransmitter.result(statement);
        } catch (SQLException exception) {
            response.appendError(exception.getMessage());
        }
        return response;
    }
}
