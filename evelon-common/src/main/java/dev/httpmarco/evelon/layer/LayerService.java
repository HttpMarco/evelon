package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthenticationService;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class LayerService {

    private static final Map<Class<? extends Layer>, Layer> layers = new HashMap<>();

    public static Layer layerOf(Class<? extends Layer> layer) {
        return layers.computeIfAbsent(layer, LayerService::createLayer);
    }

    @Contract(pure = true)
    @SneakyThrows
    private static <T extends Layer> @NotNull T createLayer(Class<T> layer) {
        var instance = layer.getConstructor().newInstance();
        if (instance instanceof ConnectableLayer<?, ?, ?> connectableLayer) {
            ConnectionAuthenticationService.appendCredentials(connectableLayer);
        }
        return instance;
    }
}