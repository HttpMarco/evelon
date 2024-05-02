package dev.httpmarco.evelon.query;

public interface QueryFilter<T> extends Query<T>, Filter<T, QueryFilter<T>> {

    QueryFilter<T> filter();

}
