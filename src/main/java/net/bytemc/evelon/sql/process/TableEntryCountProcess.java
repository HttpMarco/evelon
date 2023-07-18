package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.DatabaseConnection;
import net.bytemc.evelon.sql.DatabaseHelper;

public final class TableEntryCountProcess {

    public static int countEntries(RepositoryQuery<?> query) {
        return DatabaseConnection.executeQuery("SELECT COUNT(*) AS elements FROM " + query.getRepository().getName() +
                DatabaseHelper.getDatabaseFilterQuery(query.getFilters()) + ";", resultSet -> {
            if (resultSet.next()) {
                return resultSet.getInt("elements");
            }
            return -1;
        }, -1);
    }

}
