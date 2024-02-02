package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.filters.LayerFilterHandler;
import dev.httpmarco.evelon.common.model.Model;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class AbstractEvelonLayer implements EvelonLayer {

    private final LayerFilterHandler<?, ?> filterHandler;
    private final Model model;

    public AbstractEvelonLayer(LayerFilterHandler<?, ?> filterHandler, Model model) {
        this.filterHandler = filterHandler;
        this.model = model;
    }
}

