package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.model.Model;

public interface EvelonModelLayer<T> extends EvelonLayer<T> {

    /**
     * Gets the model for this layer.
     *
     * @return The model.
     */
    Model<?> model();

}
