package dev.httpmarco.evelon.sql.parent.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.httpmarco.evelon.common.credentials.Credentials;
import dev.httpmarco.evelon.common.layers.connection.EvelonLayerConnection;
import dev.httpmarco.evelon.sql.parent.connection.config.DefaultHikariConfig;
import dev.httpmarco.evelon.sql.parent.credentials.AbstractSqlAuthParentCredentials;
import dev.httpmarco.evelon.sql.parent.layer.ProtocolDriver;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.sql.Connection;

public class HikariConnection implements EvelonLayerConnection<Credentials, Connection> {

    private HikariDataSource dataSource;

    private final HikariConfig config;
    private final ProtocolDriver<?> protocolDriver;

    @Getter
    @Accessors(fluent = true)
    private final HikariConnectionTransmitter transmitter;

    public HikariConnection(ProtocolDriver<?> driver) {
        this.config = new DefaultHikariConfig();
        this.protocolDriver = driver;
        this.transmitter = new HikariConnectionTransmitter(this);
    }

    @SneakyThrows
    @Override
    public Connection getConnection() {
        return dataSource.getConnection();
    }

    @Override
    public void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    @Override
    public void connect(Credentials credentials) {
        // example: h2 not need auth credentials
        if(credentials instanceof AbstractSqlAuthParentCredentials authParentCredentials) {
            this.config.setUsername(authParentCredentials.username());
            this.config.setPassword(authParentCredentials.password());
        }
        this.protocolDriver.onInitialize();
        this.config.setJdbcUrl("jdbc:" + protocolDriver.jdbcStringWithMapping(credentials));
        this.dataSource = new HikariDataSource(this.config);
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
