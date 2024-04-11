package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.layer.PreppedLayer;
import dev.httpmarco.evelon.process.ProcessRunner;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class ConnectableLayer<C extends Connection<?>> extends PreppedLayer {

    public ConnectableLayer(@NotNull ConnectionAuthentication authentication, ProcessRunner runner) {
        super(authentication.id(), runner);

        ConnectionAuthenticationService.appendCredentials(this, authentication);
    }

    public abstract C connection();

}
