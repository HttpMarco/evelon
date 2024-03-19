package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.osgan.reflections.ClassScanner;
import dev.httpmarco.osgan.reflections.Reflections;
import org.jetbrains.annotations.Nullable;
import java.util.HashSet;
import java.util.Set;

public final class LayerService {

    // main layer cache
    private final Set<Layer> layers = new HashSet<>();

    public LayerService() {

        for (var layerClass : ClassScanner.of(ClassLoader.getSystemClassLoader(), Evelon.class.getPackageName())
                .criteria(aClass -> aClass.isAssignableFrom(Layer.class))
                .scan()) {

            var layer = (Layer) new Reflections<>(layerClass).allocate();

            //todo initialize layer if they need a connection or something else
            this.layers.add(layer);
        }
    }

    /**
     * Find a layer by class
     *
     * @param layer the layer object class
     * @return the layer
     */
    public @Nullable Layer find(Class<? extends Layer> layer) {
        return layers.stream().filter(it -> it.getClass().equals(layer)).findFirst().orElse(null);
    }
}
