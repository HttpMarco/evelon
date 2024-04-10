package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.layer.AbstractPreppedLayer;
import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class ConnectableLayer<CRE extends LayerConnectionCredentials, CON extends Connection<?>> extends AbstractPreppedLayer {

    private final CRE templateCredentials;

    public ConnectableLayer(CRE templateCredentials) {
        super(templateCredentials.id());
        this.templateCredentials = templateCredentials;
    }

    public abstract CON connection();

}
