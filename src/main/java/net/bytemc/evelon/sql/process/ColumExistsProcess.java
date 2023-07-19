package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.DatabaseConnection;
import net.bytemc.evelon.sql.DatabaseHelper;

import java.sql.ResultSet;

public final class ColumExistsProcess {

    public static boolean exists(RepositoryQuery<?> query) {
        return DatabaseConnection.executeQuery(("SELECT * FROM %s" + DatabaseHelper.getDatabaseFilterQuery(query.getFilters()) + ";")
                .formatted(query.getRepository().getName()), ResultSet::next, false);
    }

}
