package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import org.jetbrains.annotations.NotNull;

public final class HikariCreateProcess extends Process<String, HikariCreateProcess> {

    private static final String CREATE_VALUE_SQL = "INSERT INTO %s (%s) VALUES (%s);";

    @Override
    public String run(@NotNull RepositoryEntry entry, Layer<String> layer) {



        return CREATE_VALUE_SQL.formatted(entry.id(), "", "");
    }
}
