package dev.httpmarco.evelon.query;

public interface Query<T> extends QueryMethod<T> {

    QueryFiltering<T> filter();

}
