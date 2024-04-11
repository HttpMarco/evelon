package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriver;

public final class H2Layer extends HikariParentConnectionLayer {

    public H2Layer() {
        super(new H2ConnectionAuthentication());
    }

    @Override
    public ProtocolDriver<H2ConnectionAuthentication> protocol() {
        return new H2ProtocolDriver();
    }
}
