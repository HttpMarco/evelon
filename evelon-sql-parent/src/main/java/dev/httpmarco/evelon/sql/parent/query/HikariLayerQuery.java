package dev.httpmarco.evelon.sql.parent.query;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.layer.LayerQuery;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.query.layer.LayerFilter;
import dev.httpmarco.evelon.sql.parent.process.*;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Accessors(fluent = true)
@AllArgsConstructor
public class HikariLayerQuery<T> implements LayerQuery<T> {

    private final Layer<?> layer;
    @Getter
    private final Repository<T> associatedRepository;
    private ProcessRunner<HikariProcessReference> runner;

    @Override
    public void create(T value) {
        runner.apply(layer, new HikariCreateProcess(value), this);
    }

    @Override
    public void update(T value) {
        runner.apply(layer, new HikariUpdateProcess(value), this);
    }

    @Override
    public void delete() {
        runner.apply(layer, new HikariDeleteProcess(), this);
    }

    @Override
    public boolean exists() {
        return ((AtomicBoolean) runner.apply(layer, new HikariCheckProcess(), this)).get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findFirst() {
        var values = ((List<T>) runner.apply(layer, new HikariFindProcess(), this));
        if(!values.isEmpty()) {
            return values.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> find() {
        return (List<T>) runner.apply(layer, new HikariFindProcess(), this);
    }

    @Override
    public long count() {
        return ((AtomicLong) runner.apply(layer, new HikariCountProcess(), this)).get();
    }

    @Override
    public LayerFilter<T> filter() {
        return new HikariLayerFilter<>(layer, this.associatedRepository, runner);
    }
}
