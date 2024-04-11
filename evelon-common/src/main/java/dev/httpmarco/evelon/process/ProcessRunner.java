package dev.httpmarco.evelon.process;

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

    /**
     * Create an update process without results
     * @param repository
     * @param resolver
     */
    public void update(Repository<?> repository, ProcessResolver<Q> resolver) {
        var render = resolver.render();
        var query = render.run(repository.entry().stage(layer), repository, repository.entry());

        this.update(query);
    }

    public void query() {
        // todo
    }

    protected abstract void update(Q query);

}
