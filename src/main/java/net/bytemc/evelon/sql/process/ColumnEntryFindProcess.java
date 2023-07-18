package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.result.StageResultSet;

import java.util.ArrayList;
import java.util.List;

public final class ColumnEntryFindProcess {

    public static <T> List<T> find(RepositoryQuery<T> repositoryQuery, int amount) {
        var query = new StringBuilder("SELECT * FROM ").append(repositoryQuery.getRepository().getName());

        if (amount != -1) {
            query.append(" LIMIT ").append(amount);
        }
        query.append(";");

        // add all query filters, but only if there are any
        query.append(DatabaseHelper.getDatabaseFilterQuery(repositoryQuery.getFilters()));

        return DatabaseConnection.executeQuery(query.toString(), it -> {
            var results = new ArrayList<T>();

            while (it.next()) {
                // select the current stage of the repository
                var stage = StageHandler.getInstance().getElementStage(repositoryQuery.getRepository().repositoryClass().clazz());

                if (stage instanceof ElementStageTransformer transformer) {
                    stage = transformer.transformTo();
                }

                if (stage instanceof ElementStage<?> elementStage) {
                    //build result on current resultSet
                    var result = new StageResultSet(repositoryQuery.getRepository().repositoryClass(), it);
                    T object = elementStage.createObject(repositoryQuery.getRepository().repositoryClass(), null, result);
                    results.add(object);
                } else {
                    //todo
                }
            }
            return results;
        }, new ArrayList<>());
    }

}
