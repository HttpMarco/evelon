package net.bytemc.evelon.sql;

import net.bytemc.evelon.sql.connection.HikariDatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static final HikariDatabaseConnector pool = new HikariDatabaseConnector().createConnection();

    public static <T> T executeQuery(String query, DatabaseFunction<ResultSet, T> function, T defaultValue) {
        try (var connection = pool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                return function.apply(resultSet);
            } catch (Exception throwable) {
                return defaultValue;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (DatabaseDebugger.isEnable()) {
                System.out.println(query);
            }
        }
        return defaultValue;
    }

    public static int executeUpdate(String query) {
        try (var connection = pool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Error while executing update: " + query);
            return -1;
        } finally {
            if (DatabaseDebugger.isEnable()) {
                System.out.println(query);
            }
        }
    }
}