package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.DatabaseConnection;
import net.bytemc.evelon.sql.DatabaseHelper;

public final class ColumnEntryDeletionProcess {

    public static void delete(RepositoryQuery<?> query) {
        var deletionQuery = new StringBuilder("DELETE FROM " + query.getRepository().getName() + DatabaseHelper.getDatabaseFilterQuery(query.getFilters()) + ";");

        //todo constrains

        DatabaseConnection.executeUpdate(deletionQuery.toString());
    }

}
