package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.layer.PreppedLayer;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class ConnectableLayer<C extends Connection<?>> extends PreppedLayer {

    private final ConnectionAuthentication templateCredentials;

    public ConnectableLayer(@NotNull ConnectionAuthentication templateCredentials) {
        super(templateCredentials.id());
        this.templateCredentials = templateCredentials;

        ConnectionAuthenticationService.appendCredentials(this);
    }

    public abstract C connection();

}
