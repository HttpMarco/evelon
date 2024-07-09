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
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public final class HikariConnection implements Connection<HikariDataSource, HikariProcessReference> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikariConnection.class);

    private @Nullable HikariDataSource dataSource;
    private final ProtocolDriver<? extends ConnectionAuthentication> protocolDriver;

    @Override
    public void connect(ConnectionAuthentication credentials) {
    }

    private HikariConfig defaultConfig() {
        var config = new HikariConfig();
        return config;
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public void close() {
    }

    @Override
    public HikariDataSource connection() {
        return this.dataSource;
    }

    @Override
    public void update(@NotNull HikariProcessReference query) {
        StreamHelper.reverse(query.sqlQueries().stream()).forEach(s -> transferPreparedStatement(s.query(), PreparedStatement::execute, s.values()));
    }

    public void query(@NotNull HikariProcessReference query) {
    }

    private void transferPreparedStatement(final String query, HikariConnectionFunction<PreparedStatement> function, Object[] arguments) {
    }
}
