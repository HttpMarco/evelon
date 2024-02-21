package dev.httpmarco.evelon.h2.layer;

import dev.httpmarco.evelon.common.credentials.Credentials;
import dev.httpmarco.evelon.h2.credentials.H2SqlCredentials;
import dev.httpmarco.evelon.h2.protocol.H2ProtocolDriver;
import dev.httpmarco.evelon.sql.parent.layer.SqlParentConnectionLayer;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.nio.file.Path;

@Getter
@Accessors(fluent = true)
public class H2SqlLayer extends SqlParentConnectionLayer {

    public H2SqlLayer() {
        super("H2", new H2ProtocolDriver());
    }

    @Override
    public Credentials templateCredentials() {
        return new H2SqlCredentials();
    }
}
