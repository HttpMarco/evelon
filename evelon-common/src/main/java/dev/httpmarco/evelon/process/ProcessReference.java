package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.connection.Connection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class ProcessReference<C extends Connection<?, ?>> {

    private C connection;

}
