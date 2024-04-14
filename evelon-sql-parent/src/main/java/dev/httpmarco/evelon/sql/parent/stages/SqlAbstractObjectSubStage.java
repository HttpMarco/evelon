package dev.httpmarco.evelon.sql.parent.stages;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import dev.httpmarco.evelon.sql.parent.HikariRepositoryConstant;
import dev.httpmarco.evelon.sql.parent.SqlType;
import dev.httpmarco.evelon.stages.subs.AbstractObjectSubStage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SqlAbstractObjectSubStage extends AbstractObjectSubStage<String> {

    @Override
    public @NotNull String transform(@NotNull RepositoryEntry entry) {
        var type = SqlType.find(entry);
        // on first initialization, we put the sql type into the constants
        entry.constants().put(HikariRepositoryConstant.SQL_TYPE, type);
        // return the field name and the sql type
        return entry.id() + " " + type.name();
    }

    @Override
    public @NotNull String bindItem(List<String> transformedElements) {
        // todo add foreign keys
        return String.join(", ", transformedElements);
    }
}
