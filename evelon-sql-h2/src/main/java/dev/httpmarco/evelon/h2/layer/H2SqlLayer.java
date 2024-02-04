package dev.httpmarco.evelon.h2.layer;

import dev.httpmarco.evelon.h2.protocol.H2ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.layer.ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.layer.SqlParentConnectionLayer;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class H2SqlLayer extends SqlParentConnectionLayer {

    private final ProtocolDriver protocolDriver = new H2ProtocolDriver();

}
