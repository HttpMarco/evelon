package dev.httpmarco.evelon.process;

import java.util.function.Function;

/**
 * Runner for apply different database languages
 *
 * @param <Q> element type
 */
public abstract class ProcessRunner<Q> {

    public void update(ProcessResolver<Q> resolver) {
        this.update(resolver.render().base());
    }

    protected abstract void update(Q query);

}
