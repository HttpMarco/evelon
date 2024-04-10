package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.sql.parent.SqlParentConnectionLayer;

public class H2Layer extends SqlParentConnectionLayer<H2LayerConnectionCredentials> {

    public H2Layer() {
        super("H2", new H2LayerConnectionCredentials());
    }
}
