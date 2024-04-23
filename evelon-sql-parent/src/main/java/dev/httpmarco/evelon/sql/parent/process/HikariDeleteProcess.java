package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractEntryProcess;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.reference.HikariExecutionProcessReference;
import org.jetbrains.annotations.NotNull;

public final class HikariDeleteProcess extends AbstractEntryProcess<HikariExecutionProcessReference> {

    private static final String DELETE_SQL = "DELETE FROM %s;";

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, HikariExecutionProcessReference reference) {
        reference.append(DELETE_SQL.formatted(entry.id()));
    }
}
