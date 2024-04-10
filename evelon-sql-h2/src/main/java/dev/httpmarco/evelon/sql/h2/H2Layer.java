package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;
import dev.httpmarco.evelon.sql.parent.ProtocolDriver;

public final class H2Layer extends HikariParentConnectionLayer<H2LayerConnectionCredentials> {

    public H2Layer() {
        super(new H2LayerConnectionCredentials());
    }

    @Override
    public ProtocolDriver<H2LayerConnectionCredentials> protocol() {
        return new H2ProtocolDriver();
    }
}
