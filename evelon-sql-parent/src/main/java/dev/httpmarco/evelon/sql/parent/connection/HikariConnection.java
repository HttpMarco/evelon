package dev.httpmarco.evelon.sql.parent.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.httpmarco.evelon.common.credentials.Credentials;
import dev.httpmarco.evelon.common.layers.connection.EvelonLayerConnection;
import dev.httpmarco.evelon.sql.parent.connection.config.DefaultHikariConfig;
import dev.httpmarco.evelon.sql.parent.credentials.AbstractSqlAuthParentCredentials;
import lombok.SneakyThrows;

import java.sql.Connection;

public class HikariConnection implements EvelonLayerConnection<Credentials, Connection> {

    private HikariDataSource dataSource;
    private final HikariConfig config;

    public HikariConnection() {
        this.config = new DefaultHikariConfig();
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
        this.dataSource = new HikariDataSource(this.config);
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
