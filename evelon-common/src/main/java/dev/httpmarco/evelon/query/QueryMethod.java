package dev.httpmarco.evelon.query;

import java.util.List;

public interface QueryMethod<V> {

    void create(Query<?> query, V value);

    void update(Query<?> query, V value);

    void delete(Query<?> query);

    boolean exists(Query<?> query);

    V findFirst(Query<?> query);

    long count(Query<?> query);

    List<V> find(Query<?> query);

}
