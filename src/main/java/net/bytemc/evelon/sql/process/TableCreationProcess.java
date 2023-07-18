package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.annotations.PrimaryKey;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.substages.VirtualObjectStage;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public final class TableCreationProcess {

    /**
     * @param repository The repository to create the table for.
     * @apiNote This method is used to create a table for a repository.
     */
    public static void createTable(@NotNull Repository<?> repository) {

        // get stage of current repository class
        var stage = StageHandler.getInstance().getElementStage(repository.repositoryClass().clazz());

        if(!(stage instanceof VirtualObjectStage virtualObjectStage)) {
            System.err.println("The stage of the repository class " + repository.repositoryClass().clazz().getName() + " is not a virtual object stage. This is not supported.");
            return;
        }

        List<String> sortedTableCreationQueryList = virtualObjectStage.onParentTableCollectData(repository.getName(), repository.repositoryClass(), null);

        // turn the order of list around
        Collections.reverse(sortedTableCreationQueryList);

        for (var tableQueries : sortedTableCreationQueryList) {
            DatabaseConnection.executeUpdate(tableQueries);
        }
    }

    /**
     * @param field the field which maybe has a primary key annotation
     * @param query current sql type definition
     * @return the query with the primary key appended, if the field has a primary key annotation
     */
    public static String appendPrimaryKey(Field field, String query) {
        if(field.isAnnotationPresent(PrimaryKey.class)) {
            query = query + " PRIMARY KEY";
        }
        return query.toString();
    }
}
