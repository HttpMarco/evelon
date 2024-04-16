package dev.httpmarco.evelon.repository.query;

public interface Query<T> {

    void create(T value);

    void delete();

}
