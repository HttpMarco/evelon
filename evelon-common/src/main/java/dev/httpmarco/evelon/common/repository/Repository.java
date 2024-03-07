package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;

import java.util.List;

public interface Repository<T> {

    RepositoryClass<T> clazz();

    void addLayer(EvelonLayer<?> layer);

    List<EvelonLayer<T>> layers();

    Query<T> query();

    String name();

}
