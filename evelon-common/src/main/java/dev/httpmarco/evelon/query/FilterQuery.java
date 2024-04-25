package dev.httpmarco.evelon.query;

public interface FilterQuery<T> extends Query<T> {

    FilterQuery<T> match(String id, Object object);

}
