package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.osgan.reflections.allocator.ReflectionClassAllocater;
import dev.httpmarco.osgan.reflections.scanner.ClassScanner;

import java.util.ArrayList;
import java.util.List;

public final class RepositoryLayerPool {

    private static final List<RepositoryLayer> cachedLayers = new ArrayList<>();

    public RepositoryLayerPool() {
        for (var repos : ClassScanner.of(ClassLoader.getSystemClassLoader(), "dev.httpmarco.evelon")
                .criteria(aClass -> aClass.isAssignableFrom(RepositoryLayer.class))
                .scan()) {

            cachedLayers.add((RepositoryLayer) ReflectionClassAllocater.allocate(repos));
        }
    }
}
