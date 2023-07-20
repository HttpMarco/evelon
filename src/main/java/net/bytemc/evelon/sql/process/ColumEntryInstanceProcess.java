package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class ColumEntryInstanceProcess {

    public static void collect(Repository<?> repository) {

        List<String> neededTables = getNeededTables(repository.getName(), repository.repositoryClass());

        StringBuilder query = new StringBuilder("SELECT * FROM " + neededTables.remove(0) + " %S;");


        var primaryNames = String.join(", ", repository.repositoryClass().getPrimaries().stream().map(DatabaseHelper::getRowName).toList());
        var innerJoins = String.join(" ", neededTables.stream().map(it -> "INNER JOIN polus_element USING (" + primaryNames + ")").toList());

        DatabaseConnection.executeQuery(query.toString().formatted(innerJoins), resultSet -> {

            while (resultSet.next()) {
                System.out.println("polo");
            }
            return null;
        }, null);

    }

    private static List<String> getNeededTables(String children, RepositoryClass<?> clazz) {
        var tables = new ArrayList<String>();
        tables.add(children);

        clazz.getRows()
                .stream()
                .map(field -> new Pair<Stage<?>, Field>(StageHandler.getInstance().getElementStage(field.getType()), field))
                .filter(it -> it.left() instanceof SubElementStage<?>).forEach(it -> {
                    tables.addAll(getNeededTables(children + "_" + DatabaseHelper.getRowName(it.right()),
                            new RepositoryClass<>(it.right().getType())));
                });

        return tables;
    }
}
