package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.ProcessRunner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public abstract class Layer {

    private final String id;

    private final ProcessRunner runner;

}
