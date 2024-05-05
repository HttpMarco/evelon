package dev.httpmarco.evelon.query;

public interface QueryFiltering<T> extends Query<T>, QueryFilter<T, QueryFiltering<T>> {

    QueryFiltering<T> filter();

}
