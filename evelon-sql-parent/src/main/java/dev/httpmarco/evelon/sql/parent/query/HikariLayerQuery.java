package dev.httpmarco.evelon.sql.parent.query;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.QueryConstant;
import dev.httpmarco.evelon.query.QueryMethod;
import dev.httpmarco.evelon.sql.parent.process.*;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Accessors(fluent = true)
@AllArgsConstructor
public final class HikariLayerQuery<V> implements QueryMethod<V> {

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
        return runner.apply(layer, query, new HikariCheckProcess());
    }

    @Override
    @SuppressWarnings("unchecked")
    public V findFirst(Query<?> query) {
        var values = ((List<V>) runner.apply(layer, query, new HikariFindProcess()));
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
    @SuppressWarnings("unchecked")
    public long sum(Query<?> query, String id) {
        return ((AtomicReference<BigDecimal>) runner.apply(layer, query, new HikariMathProcess<>("SUM(" + id + ")", BigDecimal.ZERO))).get().longValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public double average(Query<?> query, String id) {
        //TODO has to be tested: maybe also use BigDecimal or BigDouble -> see sum()
        return ((AtomicReference<Double>) runner.apply(layer, query, new HikariMathProcess<>("AVG(" + id + ")", 0D))).get();
    }

    @Override
    public V min(String id) {
        // todo
        return null;
    }

    @Override
    public V max(String id) {
        //todo
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<V> order(Query<?> query, String id, Ordering ordering) {
        HikariFindProcess process = new HikariFindProcess();
        process.constants().put(QueryConstant.ORDERING, id);
        process.constants().put(QueryConstant.ORDERING_TYPE, ordering);
        return (List<V>) runner.apply(layer, query, process);
    }

    @Override
    public long count(Query<?> query) {
        return ((AtomicLong) runner.apply(layer, query, new HikariCountProcess())).get();
    }
}
