package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryEntry;

public final class HikariPreppedProcess implements Process<String> {

    private static final String TABLE_CREATE_QUERY = "CREATE TABLE IF NOT EXISTS %s(%s%s);";

    @Override
    public String run(Layer<String> layer, Repository<?> repository, RepositoryEntry entry) {
        return TABLE_CREATE_QUERY.formatted(entry.id(), "", "");
    }
}
