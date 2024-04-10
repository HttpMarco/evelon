package dev.httpmarco.evelon.layer;

public abstract class AbstractPreppedLayer extends Layer {

    public AbstractPreppedLayer(String id) {
        super(id);
    }

    public abstract void prepped();
}
