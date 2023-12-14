package net.bytemc.evelon.sql.parent.model.stages;

import net.bytemc.evelon.model.subs.AbstractVirtuellSubStage;
import net.bytemc.evelon.repository.RepositoryClass;

import java.util.ArrayList;
import java.util.List;

public final class VirtuellSubStage extends AbstractVirtuellSubStage<List<String>> {

    @Override
    public List<String> initialize(String stageId, RepositoryClass<?> clazz) {
        var queries = new ArrayList<String>();

        if (clazz.getFields().length == 0) {
            return queries;
        }

        var queryElements = new ArrayList<String>();
        // we need to create the table rows
        for (var field : clazz.getFields()) {
            var fieldElement = field.getName() + " " + "";//TODO TYPE

            //TODO check primary

            if (field.isCanNull()) {
                fieldElement += " NOT NULL";
            }
        }

        queries.add("CREATE TABLE IF NOT EXISTS " + stageId + "(" + String.join(", ", List.of()) + ")");
        return queries;
    }
}
