package dev.httpmarco.evelon.sql.parent.connection;

import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.Objects;

@AllArgsConstructor
public class HikariConnectionTransmitter {

    private HikariConnection hikariConnection;

    public int executeUpdate(final String query, Object... arguments) {
        try (var connection = hikariConnection.getConnection(); var statement = connection.prepareStatement(query)) {

            for (int i = 0; i < arguments.length; i++) {
                statement.setString(i + 1, Objects.toString(arguments[i]));
            }

            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Error while executing update: " + query);
            return -1;
        } finally {

        }
    }
}
