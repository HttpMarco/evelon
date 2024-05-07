package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Ordering;

import java.util.List;

public interface QueryMethod<V> {

    void create(Query<?> query, V value);

    void update(Query<?> query, V value);

    void delete(Query<?> query);

    boolean exists(Query<?> query);

    V findFirst(Query<?> query);

    long count(Query<?> query);

    List<V> find(Query<?> query);

    long sum(Query<?> query, String id);

    double average(Query<?> query, String id);

    V min(String id);

    V max(String id);

    List<V> order(Query<?> query, String id, Ordering ordering);

}