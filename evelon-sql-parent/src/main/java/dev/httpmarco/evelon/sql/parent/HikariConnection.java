package dev.httpmarco.evelon.sql.parent;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public final class HikariConnection implements Connection<HikariDataSource> {

    private @Nullable HikariDataSource dataSource;
    private final ProtocolDriver<? extends LayerConnectionCredentials> protocolDriver;

    @Override
    public void connect(LayerConnectionCredentials credentials) {
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

        if (protocolDriver instanceof ProtocolDriverLoader loader) {
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
}
