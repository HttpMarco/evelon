package dev.httpmarco.evelon.sql.parent.connection;

import lombok.AllArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@AllArgsConstructor
public class HikariConnectionTransmitter {

    private HikariConnection hikariConnection;

    public void executeUpdate(final String query, Object... arguments) {
        this.transferPreparedStatement(PreparedStatement::executeUpdate, query, arguments);
    }

    public void executeQuery(final String query, Object... arguments) {
        this.transferPreparedStatement(PreparedStatement::executeQuery, query, arguments);
    }

    public void transferPreparedStatement(StatementTransmitter statementTransmitter, String query, Object... arguments) {
        try (var connection = hikariConnection.getConnection(); var statement = connection.prepareStatement(query)) {
            for (int i = 0; i < arguments.length; i++) {
                statement.setString(i + 1, Objects.toString(arguments[i]));
            }
            statementTransmitter.result(statement);
        } catch (SQLException exception) {
            System.err.println("Error while executing update: " + query);
        }
    }
}
