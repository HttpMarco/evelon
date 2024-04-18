package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractEntryProcess;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.HikariExecutionReference;
import org.jetbrains.annotations.NotNull;

public final class HikariDeleteProcess extends AbstractEntryProcess<HikariExecutionReference> {

    private static final String DELETE_SQL = "DELETE FROM %s;";

    @Override
    public HikariExecutionReference run(@NotNull RepositoryExternalEntry entry) {
        var reference = new HikariExecutionReference();
        reference.stack(DELETE_SQL.formatted(entry.id()));
        return reference;
    }
}
