package dev.httpmarco.evelon.query;

public interface QueryFilter<T, Q extends QueryMethod<T>> extends QueryMethod<T> {

    Q match(String id, Object object);

}
