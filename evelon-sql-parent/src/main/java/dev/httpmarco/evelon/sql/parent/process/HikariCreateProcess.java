package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class HikariCreateProcess extends Process<String, HikariCreateProcess> {

    private static final String CREATE_VALUE_SQL = "INSERT INTO %s (%s) VALUES (%s);";

    @Override
    public String run(@NotNull RepositoryEntry entry, Layer<String> layer) {
        if (!(entry instanceof RepositoryExternalEntry objectEntry)) {
            throw new UnsupportedOperationException("Processes can only be run with external entries!");
        }

        var sqlEntries = new ArrayList<String>();
        for (var child : objectEntry.children()) {
            if (child instanceof RepositoryExternalEntry) {
                this.newSubProcess(new HikariCreateProcess());
                continue;
            }
            sqlEntries.add(child.id());
        }

        return CREATE_VALUE_SQL.formatted(entry.id(),
                String.join(", ", sqlEntries),
                String.join(", ", "?".repeat(sqlEntries.size()).split("")));
    }
}
