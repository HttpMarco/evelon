package dev.httpmarco.evelon.sql.parent.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriverLoader;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@RequiredArgsConstructor
public final class HikariConnection implements Connection<HikariDataSource> {

    private @Nullable HikariDataSource dataSource;
    private final ProtocolDriver<? extends ConnectionAuthentication> protocolDriver;

    @Override
    public void connect(ConnectionAuthentication credentials) {
        var config = new HikariConfig();

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        config.setMinimumIdle(2);
        config.setMaximumPoolSize(100);
        config.setConnectionTimeout(10_000);
        config.setValidationTimeout(10_000);

        if (protocolDriver instanceof ProtocolDriverLoader<?> loader) {
            loader.initialize();
        }

        config.setJdbcUrl(protocolDriver.jdbcUrlBinding(credentials));
        this.dataSource = new HikariDataSource(config);
    }

    @Override
    public boolean isConnected() {
        return this.dataSource != null && !this.dataSource.isClosed();
    }

    @Override
    public void close() {
        if (this.dataSource != null && !this.dataSource.isClosed()) {
            this.dataSource.close();
        }
    }

    @Override
    public HikariDataSource connection() {
        return this.dataSource;
    }

    @Override
    public void update(String query, Object... arguments) {
        this.transferPreparedStatement(query, PreparedStatement::executeUpdate, arguments);
    }

    private void transferPreparedStatement(final String query, HikariConnectionFunction<PreparedStatement, ?> function, @NotNull Object @NotNull ... arguments) {
        if (dataSource == null) {
            return;
        }
        Evelon.LOGGER.debug(query);
        try (var connection = dataSource.getConnection(); var statement = connection.prepareStatement(query)) {
            for (int i = 0; i < arguments.length; i++) {
                statement.setString(i + 1, Objects.toString(arguments[i]));
            }
            function.apply(statement);
        } catch (SQLException exception) {
            Evelon.LOGGER.error("Executing query {}", query);
            throw new RuntimeException(exception);
        }
    }
}