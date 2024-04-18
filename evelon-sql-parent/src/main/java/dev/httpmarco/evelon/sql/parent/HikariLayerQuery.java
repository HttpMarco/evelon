package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.query.Query;
import dev.httpmarco.evelon.sql.parent.process.HikariCreateProcessAbstract;
import dev.httpmarco.evelon.sql.parent.process.HikariDeleteProcess;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class HikariLayerQuery<T> implements Query<T> {

    private final Repository<T> repository;
    private ProcessRunner<HikariExecutionReference> runner;

    @Override
    public void create(T value) {
        runner.apply(new HikariCreateProcessAbstract(value), repository);
    }

    @Override
    public void delete() {
        runner.apply(new HikariDeleteProcess(), repository);
    }
}
