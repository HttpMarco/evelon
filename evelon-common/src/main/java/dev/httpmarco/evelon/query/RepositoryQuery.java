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
    public void update(T value) {
        for (var layer : repository.layers()) {
            layer.query(repository).update(value);
        }
    }

    @Override
    public void delete() {
        for (var layer : repository.layers()) {
            layer.query(repository).delete();
        }
    }

    @Override
    public boolean exists() {
        for (var layer : repository.layers()) {
            if(layer.query(repository).exists()){
                return true;
            }
        }
        return false;
    }

    @Override
    public T findFirst() {
        return null;
    }
}
