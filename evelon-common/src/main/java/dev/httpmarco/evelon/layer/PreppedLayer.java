package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.filtering.FilterHandler;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.process.ProcessReference;

public abstract class PreppedLayer<Q extends ProcessReference<Q>> extends Layer<Q> {

    public PreppedLayer(String id, FilterHandler<?, ?> filterHandler) {
        super(id, filterHandler);
    }

    public abstract void prepped(Repository<?> repository);

}
