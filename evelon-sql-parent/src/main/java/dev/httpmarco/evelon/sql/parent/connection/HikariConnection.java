package dev.httpmarco.evelon.sql.parent.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import dev.httpmarco.evelon.sql.parent.HikariDefaultAuthentication;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriverLoader;
import dev.httpmarco.osgan.utils.stream.StreamHelper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
public final class HikariConnection implements Connection<HikariDataSource, HikariProcessReference> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikariConnection.class);

    private @Nullable HikariDataSource dataSource;
    private final ProtocolDriver<? extends ConnectionAuthentication> protocolDriver;

    @Override
    public void connect(ConnectionAuthentication credentials) {
        var config = defaultConfig();

        if (protocolDriver instanceof ProtocolDriverLoader<?> loader) {
            loader.initialize();
        }

        if (credentials instanceof HikariDefaultAuthentication authentication) {
            config.setUsername(authentication.username());
            config.setPassword(authentication.password());
        }

        config.setJdbcUrl(protocolDriver.jdbcUrlBinding(credentials));
        this.dataSource = new HikariDataSource(config);
    }

    private HikariConfig defaultConfig() {
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
        return config;
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
    public void update(@NotNull HikariProcessReference query) {
        StreamHelper.reverse(query.sqlQueries().stream()).forEach(s -> transferPreparedStatement(s.query(), PreparedStatement::execute, s.values()));
    }

    @Deprecated
    public void query(@NotNull HikariProcessReference query) {
        if (!isConnected()) {
            LOGGER.error("Cannot execute query, because the connection is not established!");
            return;
        }
        StreamHelper.reverse(query.sqlQueries().stream()).forEach(s -> transferPreparedStatement(s.query(), it -> {
            var resultSet = it.executeQuery();
            while (resultSet.next()) {
                s.consumer().apply(resultSet);
            }
            query.sqlQueries().remove(s);
        }, s.values()));

        if (!query.sqlQueries().isEmpty()) {
            query(query);
        }
    }

    public <R> R query(String query, Object[] arguments, HikariStatementBuilder<R> builder) {
        try (var connection = dataSource.getConnection(); var statement = connection.prepareStatement(query)) {

            for (int i = 0; i < arguments.length; i++) {

                var parameterIndex = i + 1;
                var parameter = arguments[i];

                if (parameter == null) {
                    //todo find the right type here
                    statement.setNull(parameterIndex, Types.OTHER);
                } else {
                    statement.setString(parameterIndex, Objects.toString(parameter));
                }
            }

            return builder.apply(statement.executeQuery());
        } catch (SQLException exception) {
            LOGGER.error("{} Objects: {}", query, Arrays.toString(arguments));
            throw new RuntimeException(exception);
        }
    }

    @Deprecated
    private void transferPreparedStatement(final String query, HikariConnectionFunction<PreparedStatement> function, Object[] arguments) {
        if (dataSource == null) {
            LOGGER.warn("The datasource of evelon layer is not present! Please check your configuration.");
            return;
        }
        LOGGER.debug("{} Objects: {}", query, Arrays.toString(arguments));
        try (var connection = dataSource.getConnection(); var statement = connection.prepareStatement(query)) {
            for (int i = 0; i < arguments.length; i++) {

                var parameterIndex = i + 1;
                var parameter = arguments[i];

                if (parameter == null) {
                    //todo find the right type here
                    statement.setNull(parameterIndex, Types.OTHER);
                } else {
                    statement.setString(parameterIndex, Objects.toString(parameter));
                }
            }
            function.apply(statement);
        } catch (SQLException exception) {
            LOGGER.error("{} Objects: {}", query, Arrays.toString(arguments));
            throw new RuntimeException(exception);
        }
    }
}