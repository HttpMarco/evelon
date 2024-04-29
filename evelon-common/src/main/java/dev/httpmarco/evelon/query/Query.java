package dev.httpmarco.evelon.query;

public interface Query<T> extends QueryMethod<T> {

    QueryFilter<T> filter();

}
