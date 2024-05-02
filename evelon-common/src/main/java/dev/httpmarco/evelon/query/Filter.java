package dev.httpmarco.evelon.query;

public interface Filter<T, Q extends QueryMethod<T>> extends QueryMethod<T> {

    Q match(String id, Object object);

}
