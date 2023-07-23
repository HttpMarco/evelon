package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.substages.CollectionObjectState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ColumEntryInstanceProcess {

    public static <T> List<T> collect(RepositoryQuery<T> repositoryQuery) {

        var repository = repositoryQuery.getRepository();
        var neededTables = getNeededTables(repository.getName(), repository.repositoryClass());
        var query = new StringBuilder("SELECT * FROM " + repository.getName() + " %s;");
        var primaryNames = String.join(", ", repository.repositoryClass().getPrimaries().stream().map(DatabaseHelper::getRowName).toList());
        var innerJoins = String.join(" ", neededTables.keySet().stream().skip(1).map(it -> "INNER JOIN polus_element USING (" + primaryNames + ")").toList());

        var databaseResults = DatabaseConnection.executeQuery(query.toString().formatted(innerJoins), resultSet -> {
            var elements = new ArrayList<DatabaseResultSet>();
            while (resultSet.next()) {
                var result = new DatabaseResultSet();
                for (var tables : neededTables.keySet()) {
                    var table = result.addTable(tables);

                    for (String column : neededTables.get(tables)) {
                        table.setProperty(column, resultSet.getObject(tables + "." + column));
                    }
                }
                elements.add(result);
            }
            return elements;
        }, new ArrayList<DatabaseResultSet>());

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

            if(stage instanceof CollectionObjectState) {
                continue;
            }

            if (stage instanceof SubElementStage<?>) {
                tables.putAll(getNeededTables(children + "_" + DatabaseHelper.getRowName(row), new RepositoryClass<>(row.getType())));
            } else if (stage instanceof ElementStage<?>) {
                columns.add(DatabaseHelper.getRowName(row));
            }
        }
        tables.put(children, columns);
        return tables;
    }
}
