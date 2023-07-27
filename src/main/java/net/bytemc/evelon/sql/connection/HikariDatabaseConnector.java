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
import net.bytemc.evelon.sql.DatabaseCradinates;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public final class HikariDatabaseConnector {

    private static final String JDBC_DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
    private static final String CONNECT_URL_FORMAT = "jdbc:mariadb://%s:%d/%s?serverTimezone=UTC";

    @Getter
    private HikariDataSource hikariDataSource;

    private static final DatabaseCradinates databaseCradinates = new DatabaseCradinates(
            "127.0.0.1",
            "test123",
            "root",
            "tnw",
            3306);

    public HikariDatabaseConnector createConnection() {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(String.format(CONNECT_URL_FORMAT, databaseCradinates.hostname(), databaseCradinates.port(), databaseCradinates.database()));
        hikariConfig.setDriverClassName(JDBC_DRIVER_CLASS_NAME);
        hikariConfig.setUsername(databaseCradinates.user());
        hikariConfig.setPassword(databaseCradinates.password());

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
}
