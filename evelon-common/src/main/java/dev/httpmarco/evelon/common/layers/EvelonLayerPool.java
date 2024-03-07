package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.exceptions.LayerNotInClassloaderException;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
public final class EvelonLayerPool {

    private final Map<Class<? extends EvelonLayer<?>>, EvelonLayer<?>> cachedLayers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> EvelonLayer<T> getLayer(Class<? extends EvelonLayer<T>> layerClass) {
        if (!cachedLayers.containsKey(layerClass)) {
            // check if layer is real in class loader (not only api use)
            if (!this.checkRequirementOfLayerInitialize(layerClass)) {
                throw new LayerNotInClassloaderException(layerClass);
            }
            this.initializeLayer(layerClass);
        }
        return (EvelonLayer<T>) this.cachedLayers.get(layerClass);
    }

    @SneakyThrows
    private boolean checkRequirementOfLayerInitialize(Class<?> layer) {
        try {
            return ClassLoader.getSystemClassLoader().loadClass(layer.getName()) != null;
        } catch (ClassNotFoundException exception) {
            return false;
        }
    }

    @SneakyThrows
    private void initializeLayer(Class<? extends EvelonLayer<?>> layerClass) {
        var allocatedLayer = Reflections.of(layerClass).newInstanceWithNoArgs();
        if (allocatedLayer instanceof ConnectableEvelonLayer<?, ?, ?> connectableLayer) {
            Evelon.instance().credentialsService().addCredentials(connectableLayer);
        }
        cachedLayers.put(layerClass, allocatedLayer);
    }
}