package dev.httpmarco.evelon.query2;

public interface QueryMethod<V> {

    void create(V value);

    void update(V value);

    void delete();

    boolean exists();

}
