package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.Repository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public abstract class ProcessRunner<Q> {

    private final Layer<Q> layer;

    public void apply(@NotNull Process process, Repository<?> repository) {
        process.run(repository, layer);
    }

    public abstract void update(Q query);

    public abstract void query(Q query);

}