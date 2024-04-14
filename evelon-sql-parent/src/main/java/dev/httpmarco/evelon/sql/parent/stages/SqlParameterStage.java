package dev.httpmarco.evelon.sql.parent.stages;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.sql.parent.HikariRepositoryConstant;
import dev.httpmarco.evelon.sql.parent.SqlType;
import org.jetbrains.annotations.NotNull;

public final class SqlParameterStage implements dev.httpmarco.evelon.stages.SingleStage<String> {

    @Override
    public @NotNull String transform(@NotNull RepositoryEntry entry) {
        var type = SqlType.find(entry);
        // on first initialization, we put the sql type into the constants
        entry.constants().put(HikariRepositoryConstant.SQL_TYPE, type);
        // return the field name and the sql type
        return entry.id() + " " + type.name();
    }
}
