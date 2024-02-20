package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.exceptions.LayerNotInClassloaderException;
import dev.httpmarco.osgan.reflections.allocator.ReflectionClassAllocater;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
public final class EvelonLayerPool {

    private final Map<Class<? extends EvelonLayer<?>>, EvelonLayer<?>> cachedLayers = new HashMap<>();

    public EvelonLayer<?> getLayer(Class<? extends EvelonLayer<?>> layerClass) {
        if (!cachedLayers.containsKey(layerClass)) {
            // check if layer is real in class loader (not only api use)
            if (!this.checkRequirementOfLayerInitialize(layerClass)) {
                throw new LayerNotInClassloaderException(layerClass);
            }
            this.initializeLayer(layerClass);
        }
        return this.cachedLayers.get(layerClass);
    }

    @SneakyThrows
    private boolean checkRequirementOfLayerInitialize(Class<?> layer) {
        try {
            return ClassLoader.getSystemClassLoader().loadClass(layer.getName()) != null;
        }catch (ClassNotFoundException exception) {
           return false;
        }
    }

    private void initializeLayer(Class<? extends EvelonLayer<?>> layerClass) {
        var allocatedLayer = ReflectionClassAllocater.allocate(layerClass);
        if (allocatedLayer instanceof ConnectableEvelonLayer<?, ?, ?> connectableLayer) {
            // todo
            //connectableLayer.connection().connectWithMapping(Evelon.instance().credentialsConfig().credentials(connectableLayer));
        }
        cachedLayers.put(layerClass, allocatedLayer);
    }
}