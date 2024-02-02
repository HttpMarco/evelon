package dev.httpmarco.evelon.common.layers;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class EvelonLayerPool {

    private final List<EvelonLayer> cachedLayers = new ArrayList<>();

    public EvelonLayerPool() {

    }
}
