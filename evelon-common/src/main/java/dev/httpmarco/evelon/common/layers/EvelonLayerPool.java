package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.exception.LayerNotInClassloaderException;
import dev.httpmarco.osgan.reflections.allocator.ReflectionClassAllocater;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Accessors(fluent = true)
public final class EvelonLayerPool {

    private final Map<Class<EvelonLayer<?>>, EvelonLayer<?>> cachedLayers = new HashMap<>();

    public EvelonLayer<?> getLayer(Class<EvelonLayer<?>> layerClass) {
        if(!cachedLayers.containsKey(layerClass)) {
            // check if layer is real in class loader (not only api use)
            if(!this.checkRequirementOfLayerInitialize(layerClass)) {
                throw new LayerNotInClassloaderException();
            }
            this.initializeLayer(layerClass);
        }
        return this.cachedLayers.get(layerClass);
    }

    @SneakyThrows
    private boolean checkRequirementOfLayerInitialize(Class<?> layer) {
        return ClassLoader.getPlatformClassLoader().loadClass(layer.getName()) != null;
    }

    private void initializeLayer(Class<EvelonLayer<?>> layerClass) {
        cachedLayers.put(layerClass, (EvelonLayer<?>) ReflectionClassAllocater.allocate(layerClass));
    }
}