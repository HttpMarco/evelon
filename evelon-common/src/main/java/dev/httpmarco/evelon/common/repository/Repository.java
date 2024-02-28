package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;

public interface Repository<T> {

    RepositoryClass<T> clazz();

    void addLayer(EvelonLayer<?> layer);

    Query query();

    String name();

}
