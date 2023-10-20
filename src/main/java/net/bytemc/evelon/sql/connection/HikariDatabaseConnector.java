/*
 * Copyright 2019-2023 ByteMC team & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bytemc.evelon.sql.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.h2.H2Connection;
import org.h2.Driver;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public final class HikariDatabaseConnector {

    private static final String CONNECT_URL_FORMAT = "jdbc:%s://%s:%d/%s?serverTimezone=UTC";

    @Getter
    private static HikariDataSource hikariDataSource;

    public HikariDatabaseConnector createConnection(DatabaseProtocol databaseProtocol) {
        var hikariConfig = new HikariConfig();
        var cradinates = Evelon.getDatabaseCradinates();

        hikariConfig.setJdbcUrl(String.format(CONNECT_URL_FORMAT, databaseProtocol.toString(), cradinates.hostname(), cradinates.port(), cradinates.database()));

        if (databaseProtocol == DatabaseProtocol.H2) {
            Driver.load();
            hikariConfig.setJdbcUrl("jdbc:h2:" + H2Connection.getPath().toAbsolutePath());
        } else if (databaseProtocol == DatabaseProtocol.MARIADB) {
            try {
                Class.forName(databaseProtocol.getDriver());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // h2 and mariadb have problems with the driver, but it works -> don't touch it
            hikariConfig.setDriverClassName(databaseProtocol.getDriver());
        }

        hikariConfig.setUsername(cradinates.user());
        hikariConfig.setPassword(cradinates.password());

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");
        hikariConfig.addDataSourceProperty("useLocalSessionState", "true");
        hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
        hikariConfig.addDataSourceProperty("cacheResultSetMetadata", "true");
        hikariConfig.addDataSourceProperty("cacheServerConfiguration", "true");
        hikariConfig.addDataSourceProperty("elideSetAutoCommits", "true");
        hikariConfig.addDataSourceProperty("maintainTimeStats", "false");

        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setConnectionTimeout(10_000);
        hikariConfig.setValidationTimeout(10_000);

        this.hikariDataSource = new HikariDataSource(hikariConfig);
        return this;
    }

    public Connection getConnection() {
        try {
            return this.hikariDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        if (hikariDataSource != null && !hikariDataSource.isClosed()) {
            hikariDataSource.close();
        }
    }
}
