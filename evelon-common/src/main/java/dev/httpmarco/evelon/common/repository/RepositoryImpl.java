package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.annotations.Entity;
import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.layers.EvelonModelLayer;
import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClassImpl;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
public class RepositoryImpl<T> implements Repository<T> {

    private final String name;
    private final RepositoryClass<T> clazz;
    private final List<EvelonLayer<T>> layers;

    public RepositoryImpl(List<EvelonLayer<T>> layers, Class<T> clazz) {
        this.layers = layers;
        this.clazz = new RepositoryObjectClassImpl<>(this, clazz);

        if (clazz.isAnnotationPresent(Entity.class)) {
            this.name = clazz.getAnnotation(Entity.class).name();
        } else {
            this.name = clazz.getSimpleName();
        }
    }

    @Override
    public List<EvelonModelLayer<T>> modelLayers() {
        return layers.stream().filter(it -> it instanceof EvelonModelLayer<?>).map(it -> (EvelonModelLayer<T>) it).toList();
    }

    @Override
    public Query<T> query() {
        return new Query<>(this);
    }

    public Query<T> queryLayer(Class<EvelonLayer<?>> layer) {
        if (layers.stream().noneMatch(it -> it.getClass().equals(layer))) {
            throw new IllegalArgumentException("Layer " + layer + " is not in the current layer");
        }
        //todo
        return null;
    }
}
