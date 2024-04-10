package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class Process<Q> {

    protected Q base;

}
