package dev.httpmarco.evelon.query;

import java.util.List;

public interface QueryMethod<V> {

    void create(V value);

    void update(V value);

    void delete();

    boolean exists();

    V findFirst();

    long count();

    List<V> find();

}
