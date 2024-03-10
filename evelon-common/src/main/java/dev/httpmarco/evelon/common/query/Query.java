package dev.httpmarco.evelon.common.query;

import dev.httpmarco.evelon.common.query.response.QueryResponse;

public interface Query<T> {

    QueryResponse create(T value);

    QueryResponse deleteAll();

    T findFirst();

    boolean exists();

}
