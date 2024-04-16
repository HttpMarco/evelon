package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractEntryProcess;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import org.jetbrains.annotations.NotNull;

public final class HikariDeleteProcess extends AbstractEntryProcess<String, HikariDeleteProcess> {

    private static final String DELETE_SQL = "DELETE FROM %s;";

    @Override
    public String run(@NotNull RepositoryExternalEntry entry) {
        return DELETE_SQL.formatted(entry.id());
    }
}
