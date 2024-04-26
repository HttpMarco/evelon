package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.query.FilterQuery;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;

public final class HikariLayerFilterQuery<T> extends HikariLayerQuery<T> implements FilterQuery<T> {

    public HikariLayerFilterQuery(Repository<T> repository, ProcessRunner<HikariProcessReference> runner) {
        super(repository, runner);
    }

    @Override
    public FilterQuery<T> match(String id, Object object) {
        return null;
    }
}
