package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.Repository;

public abstract class PreppedLayer extends Layer {

    public PreppedLayer(String id, ProcessRunner runner) {
        super(id, runner);
    }

    public abstract void prepped(Repository<?> repository);

}
