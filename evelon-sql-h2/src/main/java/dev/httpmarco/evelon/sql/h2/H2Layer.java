package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.sql.parent.layer.SqlParentLayer;

public class H2Layer extends SqlParentLayer {

    public H2Layer() {
        super(new H2Credentials());
    }
}
