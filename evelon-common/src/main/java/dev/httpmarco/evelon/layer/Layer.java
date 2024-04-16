package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.ProcessRunner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public abstract class Layer<Q> {

    // general auth binding id
    private final String id;

    // executor for every process
    private final ProcessRunner<Q> runner = generateRunner();

    protected abstract ProcessRunner<Q> generateRunner();

}