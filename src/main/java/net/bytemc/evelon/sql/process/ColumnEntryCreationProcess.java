package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;

import java.util.Collections;

public final class ColumnEntryCreationProcess {

    public static <T> void create(RepositoryQuery<T> query, T value) {

        var stage = StageHandler.getInstance().getElementStage(value.getClass());

        if (stage == null) {
            throw new StageNotFoundException(value.getClass());
        }

        if (stage instanceof SubElementStage<?> subElementStage) {
            var queries = subElementStage.onAnonymousParentElement(query.getRepository().getName(), null, query.getRepository(), query.getRepository().repositoryClass(), value);
            Collections.reverse(queries);

            for (var creationQuery : queries) {
                DatabaseConnection.executeUpdate(creationQuery);
            }
        } else {
            System.err.println("The stage of the repository class " + value.getClass().getName() + " is not a sub element stage. This is not supported.");
        }
    }
}
