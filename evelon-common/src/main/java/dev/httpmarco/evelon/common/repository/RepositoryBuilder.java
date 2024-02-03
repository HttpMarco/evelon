package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.local.LocalCacheRepositoryImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RepositoryBuilder<T> {

    private final Class<T> clazz;

    // current repository settings
    private boolean useLocalStorage = false;
    private final List<Class<EvelonLayer<?>>> layerClasses = new ArrayList<>();

    public static <R> RepositoryBuilder<R> of(Class<R> clazz) {
        return new RepositoryBuilder<>(clazz);
    }

    public RepositoryBuilder<T> withLocalStorage() {
        this.useLocalStorage = true;
        return this;
    }

    public RepositoryBuilder<T> addAfter(Class<EvelonLayer<?>> clazz) {
        this.layerClasses.add(clazz);
        return this;
    }

    public Repository<T> build() {
        var repository = useLocalStorage ? new LocalCacheRepositoryImpl<>(clazz) : new RepositoryImpl<>(clazz);
        for (var layerClass : layerClasses) {
            repository.addLayer(Evelon.getInstance().layerPool().getLayer(layerClass));
        }
        return repository;
    }
}
