package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.LayerQuery;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.query.Query;
import dev.httpmarco.evelon.sql.parent.process.HikariCreateProcessAbstract;
import dev.httpmarco.evelon.sql.parent.process.HikariDeleteProcess;
import dev.httpmarco.evelon.sql.parent.process.HikariFindProcess;
import dev.httpmarco.evelon.sql.parent.process.HikariPreppedProcess;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public final class HikariLayerQuery<T> implements LayerQuery<T> {

    private final Repository<T> repository;
    private ProcessRunner<HikariExecutionReference> runner;

    @Override
    public void create(T value) {
        runner.update(new HikariCreateProcessAbstract(value), repository);
    }

    @Override
    public void delete() {
        runner.update(new HikariDeleteProcess(), repository);
    }

    // todo find a better way to handle this
    @Override
    @SuppressWarnings("unchecked")
    public List<T> find() {
        return (List<T>) runner.query(new HikariFindProcess(), repository);
    }
}
