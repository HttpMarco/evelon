package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.sql.parent.HikariParentConnectionLayer;
import dev.httpmarco.evelon.sql.parent.driver.ProtocolDriver;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class H2Layer extends HikariParentConnectionLayer<H2ConnectionAuthentication> {

    public H2Layer() {
        super(new H2ConnectionAuthentication());
    }

    @Contract(value = " -> new", pure = true)
    @Override
    public @NotNull ProtocolDriver<H2ConnectionAuthentication> protocol() {
        return new H2ProtocolDriver();
    }
}
