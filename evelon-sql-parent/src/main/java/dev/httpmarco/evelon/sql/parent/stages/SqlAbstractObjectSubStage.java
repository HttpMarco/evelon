package dev.httpmarco.evelon.sql.parent.stages;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.stages.subs.AbstractObjectSubStage;

import java.util.List;

public final class SqlAbstractObjectSubStage extends AbstractObjectSubStage<String> {

    @Override
    public String transform(RepositoryEntry entry) {
        return entry.id() + " " + entry.type().name();
    }

    @Override
    public String bindItem(List<String> transformedElements) {
        return "CREATE TABLE IF NOT EXISTS TEST(" + String.join(", ", transformedElements) + ")";
    }
}
