package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.stages.Stage;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class ProcessRunner {

    private final Layer layer;

    public void apply(@NotNull Process process, Repository<?> repository) {
        process.run(repository, layer);
    }
}
