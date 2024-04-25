package dev.httpmarco.evelon.query;

public interface Query<T> {

    void create(T value);

    void update(T value);

    void delete();

    boolean exists();

    T findFirst();

    FilterQuery<T> filter();

}