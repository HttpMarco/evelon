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

    public void update(Repository<?> repository, ProcessResolver<Q> resolver) {
        var render = resolver.render();
        var query = render.run(repository, repository.entry());

        this.update(query);
    }

    public void query() {
        // todo
    }

    protected abstract void update(Q query);

}
