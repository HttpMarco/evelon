package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClassImpl;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public class RepositoryImpl<T> implements Repository<T> {

    private final RepositoryClass<T> clazz;
    private final List<EvelonLayer<?>> currentLayers = new ArrayList<>();

    public RepositoryImpl(Class<T> clazz) {
        this.clazz = new RepositoryClassImpl<>(clazz);
    }

    @Override
    public void addLayer(EvelonLayer<?> layer) {
        currentLayers.add(layer);
    }

    @Override
    public Query query() {
        //todo
        return null;
    }
}
