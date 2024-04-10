package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;

public interface ProtocolDriverLoader<CRE extends LayerConnectionCredentials> extends ProtocolDriver<CRE> {

    void initialize();

}
