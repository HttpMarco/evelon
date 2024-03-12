package dev.httpmarco.evelon.common.query;

import dev.httpmarco.evelon.common.query.response.UpdateResponse;

public interface Query<T> {

    UpdateResponse create(T value);

    UpdateResponse deleteAll();

    T findFirst();

    boolean exists();

}
