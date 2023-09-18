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

package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.substages.CollectionObjectStage;
import net.bytemc.evelon.sql.substages.MapObjectStage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ColumEntryInstanceProcess {

    public static <T> List<T> collect(RepositoryQuery<T> repositoryQuery, int limit) {

        var repository = repositoryQuery.getRepository();
        var neededTables = getNeededTables(repository.getName(), repository.repositoryClass());
        var query = new StringBuilder("SELECT * FROM " + repository.getName() + " %s" + SQLHelper.getDatabaseFilterQuery(repositoryQuery.getFilters()) + (limit == -1 ? "" : " LIMIT " + limit) + ";");
        var primaryNames = String.join(", ", repository.repositoryClass().getPrimaries().stream().map(SQLHelper::getRowName).toList());
        var innerJoins = String.join(" ", neededTables.keySet().stream().filter(it -> !it.equalsIgnoreCase(repositoryQuery.getRepository().getName())).map(it -> "INNER JOIN " + it + " USING (" + primaryNames + ")").toList());

        var databaseResults = SQLConnection.executeQuery(query.toString().formatted(innerJoins), resultSet -> {
            var elements = new ArrayList<SQLResultSet>();
            while (resultSet.next()) {
                var result = new SQLResultSet();
                for (var tables : neededTables.keySet()) {
                    var table = result.addTable(tables);
                    for (String column : neededTables.get(tables)) {
                        table.setProperty(column, resultSet.getObject(tables + "." + column));
                    }
                }
                elements.add(result);
            }
            return elements;
        }, new ArrayList<SQLResultSet>());

        var repositoryType = repository.repositoryClass().clazz();
        var stage = StageHandler.getInstance().getElementStage(repositoryType);

        if (stage == null) {
            throw new StageNotFoundException(repositoryType);
        }
        return databaseResults.stream().map(it -> ((SubElementStage<T>) stage).createInstance(repository.getName(), null, repository.repositoryClass(), it)).toList();
    }


    private static Map<String, List<String>> getNeededTables(String children, RepositoryClass<?> clazz) {
        // table name & there columns
        var tables = new HashMap<String, List<String>>();
        var columns = new ArrayList<String>();

        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());

            if(stage instanceof CollectionObjectStage || stage instanceof MapObjectStage) {
                continue;
            }

            if (stage instanceof SubElementStage<?>) {
                tables.putAll(getNeededTables(children + "_" + SQLHelper.getRowName(row), new RepositoryClass<>(row.getType())));
            } else if (stage instanceof SQLElementStage<?>) {
                columns.add(SQLHelper.getRowName(row));
            }
        }
        tables.put(children, columns);
        return tables;
    }
}
