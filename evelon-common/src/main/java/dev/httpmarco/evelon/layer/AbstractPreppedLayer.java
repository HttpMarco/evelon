package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.repository.Repository;

public abstract class AbstractPreppedLayer extends Layer {

    public AbstractPreppedLayer(String id) {
        super(id);
    }

    public abstract void prepped(Repository<?> repository);
}
