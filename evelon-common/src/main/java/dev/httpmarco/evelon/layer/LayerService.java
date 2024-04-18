package dev.httpmarco.evelon.layer;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class LayerService {

    private static final Map<Class<? extends Layer<?>>, Layer<?>> layers = new HashMap<>();

    public static Layer<?> layerOf(Class<? extends Layer<?>> layer) {
        return layers.computeIfAbsent(layer, LayerService::createLayer);
    }

    @SneakyThrows
    @Contract("_ -> !null")
    private static <T extends Layer<?>> @NotNull T createLayer(@NotNull Class<T> layer) {
        return layer.getConstructor().newInstance();
    }
}