package dev.httpmarco.evelon.sql.parent.connection;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.builder.BuilderTransformer;
import dev.httpmarco.evelon.common.query.response.QueryResponse;
import dev.httpmarco.evelon.common.query.response.ResponseResult;
import dev.httpmarco.evelon.common.query.response.UpdateResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public final class HikariConnectionTransmitter {

    private HikariConnection hikariConnection;

    public @NotNull UpdateResponse executeUpdate(final String query, @NotNull List<Object> arguments) {
        return this.executeUpdate(query, arguments.toArray());
    }

    public @NotNull UpdateResponse executeUpdate(final String query, Object... arguments) {
        return this.transferPreparedStatement(it -> {
            it.executeUpdate();
            return new UpdateResponse();
        }, query, arguments);
    }


    public <T> @NotNull QueryResponse<T> executeQuery(final String query, BuilderTransformer<ResultSet, T> function, Object defaultValue, Object... arguments) {
        return this.transferPreparedStatement(it -> new QueryResponse<T>().result(function.apply(it.executeQuery())), query, arguments);
    }

    private <R extends ResponseResult<?>> @NotNull R transferPreparedStatement(HikariConnectionFunction<PreparedStatement, R> function, String query, Object @NotNull ... arguments) {
        Evelon.LOGGER.info("Executing query: {}", query);
        try (var connection = hikariConnection.getConnection(); var statement = connection.prepareStatement(query)) {
            for (int i = 0; i < arguments.length; i++) {
                statement.setString(i + 1, Objects.toString(arguments[i]));
            }
            return function.apply(statement);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
