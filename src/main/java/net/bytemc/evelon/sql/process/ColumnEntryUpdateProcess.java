package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;
import java.util.HashMap;

public final class ColumnEntryUpdateProcess {

    public static <T> void update(RepositoryQuery<T> query, T value) {

        for (var primary : query.getRepository().repositoryClass().getPrimaries()) {
            query.filter().match(DatabaseHelper.getRowName(primary), Reflections.readField(value, primary)).complete();
        }

        var sqlQuery = new StringBuilder("UPDATE %s SET %s" + DatabaseHelper.getDatabaseFilterQuery(query.getFilters())).append(";");
        var elements = new HashMap<String, String>();
        var stage = StageHandler.getInstance().getElementStage(value.getClass());

        if (stage == null) {
            System.err.println("Stage not found for " + value.getClass().getName());
            return;
        }

        if (stage instanceof ElementStage<?> elementStage) {
            elements.putAll(elementStage.anonymousElementEntryData(query.getRepository().repositoryClass(), null, value));
        } else {
            //todo
        }

        var values = String.join(", ", elements.keySet().stream().map(it -> "%s = %s".formatted(it, elements.get(it))).toList());
        DatabaseConnection.executeUpdate(sqlQuery.toString().formatted(query.getRepository().getName(), values));
    }
}
