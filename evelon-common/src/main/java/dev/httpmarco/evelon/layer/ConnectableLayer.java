package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.credentials.Credentials;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class ConnectableLayer<R extends Credentials, C> extends Layer {

    private final Credentials templateCredentials;

    public abstract LayerConnection<R, C> connection();

}