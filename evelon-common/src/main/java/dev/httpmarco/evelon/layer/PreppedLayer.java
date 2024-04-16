package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.filtering.FilterHandler;
import dev.httpmarco.evelon.repository.Repository;

public abstract class PreppedLayer<Q> extends Layer<Q> {

    public PreppedLayer(String id, FilterHandler<?, ?> filterHandler) {
        super(id, filterHandler);
    }

    public abstract void prepped(Repository<?> repository);

}
