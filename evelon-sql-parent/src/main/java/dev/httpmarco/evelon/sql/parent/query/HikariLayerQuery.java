package dev.httpmarco.evelon.sql.parent.query;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.query.Query;
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
    public void create(Query<?> query, V value) {
        runner.apply(layer, query, new HikariCreateProcess(value));
    }

    @Override
    public void update(Query<?> query, V value) {
        runner.apply(layer, query, new HikariUpdateProcess(value));
    }

    @Override
    public void delete(Query<?> query) {
        runner.apply(layer, query, new HikariDeleteProcess());
    }

    @Override
    public boolean exists(Query<?> query) {
        return ((AtomicBoolean) runner.apply(layer, query, new HikariCheckProcess())).get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V findFirst(Query<?> query) {
        var values = ((List<V>) runner.apply(layer, query, new HikariFindProcess(1)));
        if (!values.isEmpty()) {
            return values.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<V> find(Query<?> query) {
        return (List<V>) runner.apply(layer, query, new HikariFindProcess());
    }

    @Override
    public long count(Query<?> query) {
        return ((AtomicLong) runner.apply(layer, query, new HikariCountProcess())).get();
    }
}
