package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.filtering.FilterHandler;
import dev.httpmarco.evelon.layer.PreppedLayer;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
public abstract class ConnectableLayer<C extends Connection<?, ?>, Q> extends PreppedLayer<Q> {

    private final ConnectionAuthentication templateAuthentication;

    public ConnectableLayer(@NotNull ConnectionAuthentication authentication, FilterHandler<?, ?> filterHandler) {
        super(authentication.id(), filterHandler);
        this.templateAuthentication = authentication;
    }

    public abstract C connection();

}
