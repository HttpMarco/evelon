package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.Repository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public abstract class ProcessRunner<Q> {

    private final Layer<Q> layer;

    public void apply(@NotNull Process<Q, ?> process, @NotNull Repository<?> repository) {
        var query = process.run(repository.entry(), layer);

        // todo add query method
        this.update(query);
    }

    public abstract void update(Q query);

}