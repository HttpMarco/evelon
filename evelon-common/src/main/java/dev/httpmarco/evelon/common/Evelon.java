package dev.httpmarco.evelon.common;

import dev.httpmarco.evelon.common.layers.EvelonLayerPool;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Evelon {

    private static Evelon instance;

    private final EvelonLayerPool layerPool = new EvelonLayerPool();

    public static Evelon getInstance() {
        if (instance == null) {
            return instance = new Evelon();
        }
        return instance;
    }

}
