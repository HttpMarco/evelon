package dev.httpmarco.evelon.document.redis.query;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.QueryMethod;

import java.util.List;

public class RedisDocumentQuery<V> implements QueryMethod<V> {
    @Override
    public void create(Query<?> query, V value) {

    }

    @Override
    public void update(Query<?> query, V value) {

    }

    @Override
    public void delete(Query<?> query) {

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
        return 0;
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
