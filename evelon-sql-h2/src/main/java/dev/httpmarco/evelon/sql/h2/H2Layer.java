package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;

public class H2Layer extends HikariParentConnectionLayer<H2LayerConnectionCredentials> {

    public H2Layer() {
        super(new H2LayerConnectionCredentials());
    }
}
