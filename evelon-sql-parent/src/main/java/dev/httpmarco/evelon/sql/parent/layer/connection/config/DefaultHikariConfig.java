package dev.httpmarco.evelon.sql.parent.layer.connection.config;

import com.zaxxer.hikari.HikariConfig;

public class DefaultHikariConfig extends HikariConfig {

    public DefaultHikariConfig() {
        addDataSourceProperty("cachePrepStmts", "true");
        addDataSourceProperty("prepStmtCacheSize", "250");
        addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        addDataSourceProperty("useServerPrepStmts", "true");
        addDataSourceProperty("useLocalSessionState", "true");
        addDataSourceProperty("rewriteBatchedStatements", "true");
        addDataSourceProperty("cacheResultSetMetadata", "true");
        addDataSourceProperty("cacheServerConfiguration", "true");
        addDataSourceProperty("elideSetAutoCommits", "true");
        addDataSourceProperty("maintainTimeStats", "false");

        setMinimumIdle(2);
        setMaximumPoolSize(100);
        setConnectionTimeout(10_000);
        setValidationTimeout(10_000);
    }
}