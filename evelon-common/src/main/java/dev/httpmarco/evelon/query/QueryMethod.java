package dev.httpmarco.evelon.query;

public interface QueryMethod<V> {

    void create(V value);

    void update(V value);

    void delete();

    boolean exists();

    V findFirst();

}
