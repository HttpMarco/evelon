package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.layer.PreppedLayer;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class ConnectableLayer<Q, A extends ConnectionAuthentication, C extends Connection<?>> extends PreppedLayer {

    private final A templateCredentials;

    public ConnectableLayer(@NotNull A templateCredentials) {
        super(templateCredentials.id());
        this.templateCredentials = templateCredentials;
    }

    public abstract C connection();

}
