package dev.httpmarco.evelon.document.redis.query;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.document.redis.RedisProcessReference;
import dev.httpmarco.evelon.document.redis.process.RedisCountProcess;
import dev.httpmarco.evelon.document.redis.process.RedisCreateProcess;
import dev.httpmarco.evelon.document.redis.process.RedisDeleteProcess;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.QueryMethod;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RedisDocumentQuery<V> implements QueryMethod<V> {

    private final Layer<?> layer;
    private ProcessRunner<RedisProcessReference> runner;

    @Override
    public void create(Query<?> query, V value) {
        runner.apply(layer, query, new RedisCreateProcess(value));
    }

    @Override
    public void update(Query<?> query, V value) {

    }

    @Override
    public void delete(Query<?> query) {
        runner.apply(layer, query, new RedisDeleteProcess());
    }

    @Override
    public boolean exists(Query<?> query) {
        return false;
    }

    @Override
    public V findFirst(Query<?> query) {
        return null;
    }

    @Override
    public long count(Query<?> query) {
        return runner.apply(layer, query, new RedisCountProcess());
    }

    @Override
    public List<V> find(Query<?> query) {
        return List.of();
    }

    @Override
    public long sum(Query<?> query, String id) {
        return 0;
    }

    @Override
    public double average(Query<?> query, String id) {
        return 0;
    }

    @Override
    public V min(String id) {
        return null;
    }

    @Override
    public V max(String id) {
        return null;
    }

    @Override
    public List<V> order(Query<?> query, String id, Ordering ordering) {
        return List.of();
    }
}
