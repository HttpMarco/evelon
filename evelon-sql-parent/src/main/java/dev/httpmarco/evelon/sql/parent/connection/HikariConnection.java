package dev.httpmarco.evelon.sql.parent.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.httpmarco.evelon.common.layers.connection.RepositoryConnection;
import lombok.SneakyThrows;

import java.sql.Connection;

public class HikariConnection implements RepositoryConnection<Connection> {

    private HikariDataSource dataSource;
    private final HikariConfig config;

    public HikariConnection(HikariConfig config) {
        this.config = config;
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
    public void connect() {
        this.dataSource = new HikariDataSource(this.config);
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
