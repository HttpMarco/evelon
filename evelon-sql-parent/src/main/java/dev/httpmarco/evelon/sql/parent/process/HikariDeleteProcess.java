package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import org.jetbrains.annotations.NotNull;

public final class HikariDeleteProcess extends UpdateProcess<HikariProcessReference> {

    private static final String DELETE_SQL = "DELETE FROM %s;";

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, HikariProcessReference reference) {
        reference.append(DELETE_SQL.formatted(entry.id()));
    }
}
