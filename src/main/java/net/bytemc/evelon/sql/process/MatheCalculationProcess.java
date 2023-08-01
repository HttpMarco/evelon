package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.DatabaseConnection;
import net.bytemc.evelon.sql.DatabaseHelper;

public final class MatheCalculationProcess {

    public static <T> int count(RepositoryQuery<T> query) {
        return DatabaseConnection.executeQuery(
                DatabaseHelper.count("count_value", (query.getRepository().getName() + DatabaseHelper.getDatabaseFilterQuery(query.getFilters()))),
                resultSet -> {
                    if (resultSet.next()) {
                        return resultSet.getInt("count_value");
                    }
                    return -1;
                }, -1);
    }
}
