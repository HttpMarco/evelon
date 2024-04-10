package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class HikariParentConnectionLayer<CRE extends LayerConnectionCredentials> extends ConnectableLayer<CRE, HikariConnection> {

    private final HikariConnection connection;

    public HikariParentConnectionLayer(CRE templateCredentials) {
        super(templateCredentials);
        this.connection = new HikariConnection(protocol());
    }

    public ProtocolDriver<CRE> protocol() {
        return LayerConnectionCredentials::id;
    }
}
