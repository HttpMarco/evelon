package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.LayerQuery;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.sql.parent.process.HikariCreateProcess;
import dev.httpmarco.evelon.sql.parent.process.HikariDeleteProcess;
import dev.httpmarco.evelon.sql.parent.process.HikariFindProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public final class HikariLayerQuery<T> implements LayerQuery<T> {

    private final Repository<T> repository;
    private ProcessRunner<HikariProcessReference> runner;

    @Override
    public void create(T value) {
        runner.apply(new HikariCreateProcess(value), repository);
    }

    @Override
    public void delete() {
        runner.apply(new HikariDeleteProcess(), repository);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> find() {
        return (List<T>) runner.apply(new HikariFindProcess(),  repository);
    }
}
