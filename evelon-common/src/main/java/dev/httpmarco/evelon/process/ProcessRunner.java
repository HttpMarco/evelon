package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.AbstractLayer;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.Repository;
import lombok.AllArgsConstructor;

/**
 * Runner for apply different database languages
 *
 * @param <Q> element type
 */
@AllArgsConstructor
public abstract class ProcessRunner<Q> {

    private final Layer<Q> layer;

    public void update(Repository<?> repository, ProcessResolver<Q> resolver) {
        this.update(resolver.render().run(repository.entry().stage().get(layer), repository, repository.entry()));
    }

    protected abstract void update(Q query);

}
