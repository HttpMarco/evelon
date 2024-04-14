package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.repository.Repository;

public abstract class PreppedLayer<Q> extends Layer<Q> {

    public PreppedLayer(String id) {
        super(id);
    }

    public abstract void prepped(Repository<?> repository);

}
