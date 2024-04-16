package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import org.jetbrains.annotations.NotNull;

public final class HikariDeleteProcess extends Process<String, HikariDeleteProcess> {

    private static final String DELETE_SQL = "DELETE FROM %s;";

    @Override
    public String run(@NotNull RepositoryEntry entry, Layer<String> layer) {
        return DELETE_SQL.formatted(entry.id());
    }
}
