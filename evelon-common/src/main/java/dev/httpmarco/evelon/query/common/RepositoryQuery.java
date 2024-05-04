package dev.httpmarco.evelon.query.common;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.QueryFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class RepositoryQuery<T> implements Query<T> {

    private final Repository<T> associatedRepository;

    @Override
    public void create(T value) {
        this.executeLayers(layer -> layer.query(associatedRepository).create(value));
    }

    @Override
    public void update(T value) {
        this.executeLayers(layer -> layer.query(associatedRepository).update(value));
    }

    @Override
    public void delete() {
        this.executeLayers(layer -> layer.query(associatedRepository).delete());
    }

    @Override
    public boolean exists() {
        for (var layer : associatedRepository.layers()) {
            if (layer.query(associatedRepository).exists()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T findFirst() {
        for (var layer : associatedRepository.layers()) {
            var value = layer.query(associatedRepository).findFirst();
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    @Override
    public QueryFilter<T> filter() {
        // todo
        return new RepositoryFilter<>(this.associatedRepository, associatedRepository.layers());
    }

    private void executeLayers(Consumer<Layer<?>> layerCallback) {
        for (var layer : associatedRepository.layers()) {
            layerCallback.accept(layer);
        }
    }
}
