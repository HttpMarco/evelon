package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.RepositoryEntry;

public final class HikariPreppedProcess extends Process<String> {

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS %s (%s%s);";

    @Override
    public String run(RepositoryEntry entry, Layer<?> layer) {


        return CREATE_TABLE_SQL.formatted(entry.id(), "", "");
    }
}
