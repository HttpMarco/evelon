package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.layer.Layer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class Query<V> implements QueryMethod<V> {

    private final Repository<V> associatedRepository;
    private final Layer<?>[] usedLayers;

    public Query(Repository<V> repository, @NotNull Set<Layer<?>> usedLayers) {
        this.associatedRepository = repository;
        this.usedLayers = usedLayers.toArray(Layer[]::new);
    }

    @Override
    public void create(V value) {
        for (var layer : this.usedLayers) {
            layer.queryMethod().create(value);
        }
    }

    @Override
    public void update(V value) {
        for (var layer : this.usedLayers) {
            layer.queryMethod().update(value);
        }
    }

    @Override
    public void delete() {
        for (var layer : usedLayers) {
            layer.queryMethod().delete();
        }
    }

    @Override
    public boolean exists() {
        for (var layer : usedLayers) {
            if (layer.queryMethod().exists()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V findFirst() {
        for (var layer : usedLayers) {
            var value = layer.queryMethod().findFirst();
            if (value != null) {
                return (V) value;
            }
        }
        return null;
    }
}
