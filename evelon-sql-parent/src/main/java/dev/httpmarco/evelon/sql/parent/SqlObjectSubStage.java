package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.stages.subs.ObjectSubStage;

import java.util.List;

public final class SqlObjectSubStage extends ObjectSubStage<String> {

    @Override
    public String transform(RepositoryEntry entry) {
        return entry.id() + " " + entry.type().name();
    }

    @Override
    public String bindItem(List<String> transformedElements) {
        return "CREATE TABLE IF NOT EXISTS TEST(" + String.join(", ", transformedElements) + ")";
    }
}
