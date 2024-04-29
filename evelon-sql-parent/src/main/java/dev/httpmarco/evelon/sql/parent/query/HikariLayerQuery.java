package dev.httpmarco.evelon.sql.parent.query;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.layer.LayerQuery;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.query.layer.LayerFilter;
import dev.httpmarco.evelon.sql.parent.process.*;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
public class HikariLayerQuery<T> implements LayerQuery<T> {

    private final Layer<?> layer;
    private final Repository<T> repository;
    private ProcessRunner<HikariProcessReference> runner;

    @Override
    public void create(T value) {
        runner.apply(new HikariCreateProcess(value), repository);
    }

    @Override
    public void update(T value) {
        runner.apply(new HikariUpdateProcess(value), repository);
    }

    @Override
    public void delete() {
        runner.apply(new HikariDeleteProcess(), repository);
    }

    @Override
    public boolean exists() {
        return ((AtomicBoolean) runner.apply(new HikariCheckProcess(), repository)).get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findFirst() {
        return ((List<T>) runner.apply(new HikariFindProcess(0, 1), repository)).get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> find() {
        return (List<T>) runner.apply(new HikariFindProcess(), repository);
    }

    @Override
    public long count() {
        return ((AtomicLong) runner.apply(new HikariCountProcess(), repository)).get();
    }

    @Override
    public LayerFilter<T> filter() {
        return new HikariLayerFilter<>(layer, this.repository, runner);
    }
}
