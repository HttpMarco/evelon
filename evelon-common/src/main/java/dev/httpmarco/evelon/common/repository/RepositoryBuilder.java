package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.local.LocalCacheRepositoryImpl;
import dev.httpmarco.evelon.common.local.LocalStorageBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RepositoryBuilder<T> {

    private final Class<T> clazz;

    // current repository settings
    private boolean useLocalStorage = false;
    private List<Class<? extends EvelonLayer<?>>> layerClasses = new ArrayList<>();

    protected RepositoryBuilder(Class<T> clazz, boolean useLocalStorage, List<Class<? extends EvelonLayer<?>>> layerClasses) {
        this.clazz = clazz;
        this.useLocalStorage = useLocalStorage;
        this.layerClasses = layerClasses;
    }

    public static <R> RepositoryBuilder<R> of(Class<R> clazz) {
        return new RepositoryBuilder<>(clazz);
    }

    public LocalStorageBuilder<T> withLocalStorage() {
        return new LocalStorageBuilder<>(this.clazz, this.layerClasses);
    }

    public RepositoryBuilder<T> addAfter(Class<? extends EvelonLayer<?>> clazz) {
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
