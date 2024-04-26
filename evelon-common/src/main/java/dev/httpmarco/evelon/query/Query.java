package dev.httpmarco.evelon.query;

import org.jetbrains.annotations.Nullable;

public interface Query<T> {

    void create(T value);

    void update(T value);

    void delete();

    boolean exists();

    @Nullable
    T findFirst();

    FilterQuery<T> filter();

}