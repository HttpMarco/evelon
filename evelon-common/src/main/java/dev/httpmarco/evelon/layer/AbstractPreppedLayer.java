package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.Repository;

public abstract class AbstractPreppedLayer<Q> extends Layer<Q> {

    public AbstractPreppedLayer(String id, ProcessRunner<Q> runner) {
        super(id, runner);
    }

    public abstract void prepped(Repository<?> repository);
}
