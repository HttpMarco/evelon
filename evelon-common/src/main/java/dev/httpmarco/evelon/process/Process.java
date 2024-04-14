package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryEntry;

public abstract class Process<Q> {

    public abstract Q run(RepositoryEntry entry, Layer<Q> layer);

}
