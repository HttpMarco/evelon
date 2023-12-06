package net.bytemc.evelon.layers;

import net.bytemc.evelon.local.LocalStorageLayer;

import java.util.HashMap;
import java.util.Map;

public final class RepositoryLayerHandler {

    private final Map<Class<? extends RepositoryLayer>, RepositoryLayer> layers = new HashMap<>();

    public RepositoryLayerHandler() {
        this.registerLayer(new LocalStorageLayer());
    }

    public void registerLayer(RepositoryLayer layer) {
        layers.put(layer.getClass(), layer);
    }

    public RepositoryLayer getLayer(Class<? extends RepositoryLayer> clazz) {
        return layers.get(clazz);
    }
}
