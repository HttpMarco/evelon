package net.bytemc.evelon.sql.parent.model.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;

import java.sql.Connection;

public final class HikariClientConnect {

    private final HikariDataSource source;

    public HikariClientConnect(HikariConfig hikariConfig) {
        this.source = new HikariDataSource(hikariConfig);
    }

    @SneakyThrows
    public Connection connection() {
        return source.getConnection();
    }

    public void close() {
        source.close();
    }
}
