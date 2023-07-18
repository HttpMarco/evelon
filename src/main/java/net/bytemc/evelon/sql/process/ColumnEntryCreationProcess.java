package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.DatabaseConnection;
import net.bytemc.evelon.sql.ElementStage;
import net.bytemc.evelon.sql.ElementStageTransformer;
import net.bytemc.evelon.sql.StageHandler;

import java.util.HashMap;

public final class ColumnEntryCreationProcess {

    public static <T> void create(RepositoryQuery<T> query, T value) {

        var stage = StageHandler.getInstance().getElementStage(value.getClass());

        if (stage == null) {
            System.err.println("The stage of the class " + value.getClass().getSimpleName() + " is null.");
            return;
        }

        var mapEntryData = new HashMap<String, String>();

        // collect all stages that are needed to create the entry
        if (stage instanceof ElementStage<?> elementStage) {
            mapEntryData.putAll(elementStage.anonymousElementEntryData(query.getRepository().repositoryClass(), null, value));
        } else if (stage instanceof ElementStageTransformer transformer) {
            //transformer class can be use to link to other tables
            if (transformer.transformTo() instanceof ElementStage<?> elementStage) {
                mapEntryData.putAll(elementStage.anonymousElementEntryData(query.getRepository().repositoryClass(), null, value));
            } else {
                //todo
            }
        } else {
            //todo
        }

        DatabaseConnection.executeUpdate("INSERT INTO %s(%s) VALUES (%s);".formatted(query.getRepository().getName(), String.join(", ", mapEntryData.keySet()), String.join(", ", mapEntryData.values())));
    }
}
