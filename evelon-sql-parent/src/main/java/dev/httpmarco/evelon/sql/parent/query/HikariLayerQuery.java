package dev.httpmarco.evelon.sql.parent.query;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.query.QueryMethod;
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
public class HikariLayerQuery<V> implements QueryMethod<V> {

    private final Layer<?> layer;
    @Getter
    private final Repository<?> associatedRepository;
    private ProcessRunner<HikariProcessReference> runner;

    @Override
    public void create(V value) {
        runner.apply(layer, new HikariCreateProcess(value), associatedRepository);
    }

    @Override
    public void update(V value) {
        runner.apply(layer, new HikariUpdateProcess(value), associatedRepository);
    }

    @Override
    public void delete() {
        runner.apply(layer, new HikariDeleteProcess(), associatedRepository);
    }

    @Override
    public boolean exists() {
        return ((AtomicBoolean) runner.apply(layer, new HikariCheckProcess(), associatedRepository)).get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V findFirst() {
        var values = ((List<V>) runner.apply(layer, new HikariFindProcess(), associatedRepository));
        if(!values.isEmpty()) {
            return values.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<V> find() {
        return (List<V>) runner.apply(layer, new HikariFindProcess(), associatedRepository);
    }

    @Override
    public long count() {
        return ((AtomicLong) runner.apply(layer, new HikariCountProcess(), associatedRepository)).get();
    }
}
