package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.layer.AbstractPreppedLayer;
import dev.httpmarco.evelon.process.ProcessRunner;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class ConnectableLayer<Q, CRE extends ConnectionCredentials, CON extends Connection<?>> extends AbstractPreppedLayer<Q> {

    private final CRE templateCredentials;

    public ConnectableLayer(@NotNull CRE templateCredentials, ProcessRunner<Q> runner) {
        super(templateCredentials.id(), runner);
        this.templateCredentials = templateCredentials;
    }

    public abstract CON connection();

}
