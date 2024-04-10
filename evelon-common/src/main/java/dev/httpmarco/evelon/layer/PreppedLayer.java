package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.repository.Repository;

public interface PreppedLayer<Q> extends Layer<Q> {

    void prepped(Repository<?> repository);

}
