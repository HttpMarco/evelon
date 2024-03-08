package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.layers.EvelonModelLayer;
import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.query.intern.DataQuery;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClassImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import java.util.List;

@Getter
@Accessors(fluent = true)
public class RepositoryImpl<T> implements Repository<T> {

    private final RepositoryClass<T> clazz;
    private final List<EvelonLayer<T>> layers;

    public RepositoryImpl(List<EvelonLayer<T>> layers, Class<T> clazz) {
        this.layers = layers;
        this.clazz = new RepositoryObjectClassImpl<>(this, clazz);
    }

    @Override
    public List<EvelonModelLayer<T>> modelLayers() {
        return layers.stream().filter(it -> it instanceof EvelonModelLayer<?>).map(it -> (EvelonModelLayer<T>) it).toList();
    }

    @Override
    public Query<T> query() {
        return new DataQuery<>(this);
    }

    public Query<T> queryLayer(EvelonLayer<?> layer) {
        if (!layers.contains(layer)) {
            throw new IllegalArgumentException("Layer " + layer + " is not in the current layer");
        }
        //todo
        return null;
    }

    @Override
    public String name() {
        return clazz().clazz().getSimpleName();
    }
}
