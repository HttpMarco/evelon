package dev.httpmarco.evelon.query.common;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.QueryFiltering;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.function.Consumer;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class RepositoryQuery<T> {

    private final Repository<T> associatedRepository;

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
    public QueryFiltering<T> filter() {
        return new RepositoryFiltering<>(this.associatedRepository, associatedRepository.layers());
    }

    private void executeLayers(Consumer<Layer<?>> layerCallback) {
        for (var layer : associatedRepository.layers()) {
            layerCallback.accept(layer);
        }
    }
}
