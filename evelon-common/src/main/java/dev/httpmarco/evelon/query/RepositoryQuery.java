package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Repository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class RepositoryQuery<T> implements Query<T> {

    private final Repository<T> repository;

    @Override
    public void create(T value) {
        for (var layer : repository.layers()) {
            layer.query(repository).create(value);
        }
    }

    @Override
    public void delete() {
        for (var layer : repository.layers()) {
            layer.query(repository).delete();
        }
    }
}
