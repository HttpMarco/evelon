package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.RepositoryEntry;

public final class HikariExistsProcess extends Process<String, HikariExistsProcess> {

    private static final String EXISTS_SQL = "SELECT * FROM %s:";

    @Override
    public String run(RepositoryEntry entry, Layer<String> layer) {
        return EXISTS_SQL;
    }
}
