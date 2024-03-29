package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.layers.ConnectableEvelonLayer;
import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.local.LocalCacheRepositoryImpl;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RepositoryBuilder<T> {

    private final Class<T> clazz;

    // current repository settings
    private boolean useLocalStorage = false;
    private List<Class<? extends EvelonLayer<T>>> layerClasses = new ArrayList<>();

    protected RepositoryBuilder(Class<T> clazz, boolean useLocalStorage, List<Class<? extends EvelonLayer<T>>> layerClasses) {
        this.clazz = clazz;
        this.useLocalStorage = useLocalStorage;
        this.layerClasses = layerClasses;
    }

    public static <R> RepositoryBuilder<R> of(Class<R> clazz) {
        return new RepositoryBuilder<>(clazz);
    }

    @SuppressWarnings("unchecked")
    public RepositoryBuilder<T> addAfter(Class<? extends EvelonLayer<?>> clazz) {
        this.layerClasses.add((Class<? extends EvelonLayer<T>>) clazz);
        return this;
    }

    public Repository<T> build() {

        // pre - load layers todo better implementation Move to .addAfter
        for (var layerClass : layerClasses) {
            var layer = Evelon.instance().layerPool().getLayer(layerClass);
        }

        var layers = new ArrayList<EvelonLayer<T>>();
        for (var layerClass : layerClasses) {

            var layer = Evelon.instance().layerPool().getLayer(layerClass);
            if (layer instanceof ConnectableEvelonLayer<?, ?, ?> connectableLayer) {
                if (Evelon.instance().credentialsService().isPresent(connectableLayer)) {
                    layers.add(layer);
                } else {
                    Evelon.LOGGER.warn("Credentials not found for layer: {} - {} (layer disabled)", layer.id(), layer.getClass().getSimpleName());
                }
            } else {
                layers.add(layer);
            }
        }
        var repository = useLocalStorage ? new LocalCacheRepositoryImpl<>(layers, clazz) : new RepositoryImpl<>(layers, clazz);

        for (var layer : repository.layers()) {
            if (layer instanceof InitializeRepository initializeRepository) {
                initializeRepository.initializeRepository(repository);
            }
        }

        return repository;
    }
}
