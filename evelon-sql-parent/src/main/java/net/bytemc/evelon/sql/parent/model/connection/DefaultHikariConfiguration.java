package net.bytemc.evelon.sql.parent.model.connection;

import com.zaxxer.hikari.HikariConfig;

public final class DefaultHikariConfiguration extends HikariConfig {

    private static final String CONNECT_URL = "jdbc:%s://%s:%d/%s?serverTimezone=UTC";

    public DefaultHikariConfiguration() {
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
