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

package net.bytemc.evelon.sql;

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.sql.connection.HikariDatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SQLConnection {

    private static HikariDatabaseConnector POOL;

    public static void init(DatabaseProtocol databaseProtocol) {
        POOL = new HikariDatabaseConnector().createConnection(databaseProtocol);
        Debugger.log("Established connection to " + databaseProtocol + " server");
    }

    public static <T> T executeQuery(String query, SQLFunction<ResultSet, T> function, T defaultValue) {
        try (var connection = POOL.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                return function.apply(resultSet);
            } catch (Exception throwable) {
                return defaultValue;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (Debugger.isEnable()) {
                System.out.println(query);
            }
        }
        return defaultValue;
    }

    public static int executeUpdate(String query) {
        try (var connection = POOL.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Error while executing update: " + query);
            return -1;
        } finally {
            if (Debugger.isEnable()) {
                System.out.println(query);
            }
        }
    }
}