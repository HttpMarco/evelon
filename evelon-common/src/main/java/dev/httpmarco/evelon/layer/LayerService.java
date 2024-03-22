package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.osgan.reflections.Reflections;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public final class LayerService {

    // main layer cache
    private final Set<Layer> layers = new HashSet<>();

    /**
     * Register a layer
     *
     * @param layerClass the layer class
     * @return the layer
     */
    private Layer registerLayer(Class<? extends Layer> layerClass) {
        var layer = new Reflections<>(layerClass).newInstanceWithNoArgs();

        if (layer instanceof ConnectableLayer<?> connectableLayer) {
            Evelon.instance().credentialsService().addCredentials(connectableLayer);
            connectableLayer.initialize();
        }

        this.layers.add(layer);
        return layer;
    }

    /**
     * Find a layer by class
     *
     * @param layer the layer object class
     * @return the layer
     */
    public @Nullable Layer find(Class<? extends Layer> layer) {
        return layers.stream().filter(it -> it.getClass().equals(layer)).findFirst().orElseGet(() -> this.registerLayer(layer));
    }
}
