package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractEntryProcess;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.HikariExecutionReference;
import org.jetbrains.annotations.NotNull;

public final class HikariDeleteProcess extends AbstractEntryProcess<HikariExecutionReference> {

    private static final String DELETE_SQL = "DELETE FROM %s;";

    @Override
    public HikariExecutionReference run(@NotNull RepositoryExternalEntry entry) {
        return new HikariExecutionReference().bind(DELETE_SQL.formatted(entry.id()));
    }
}
