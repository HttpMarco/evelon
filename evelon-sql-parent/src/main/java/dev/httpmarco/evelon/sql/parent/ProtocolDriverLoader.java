package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectionCredentials;

public interface ProtocolDriverLoader<CRE extends ConnectionCredentials> extends ProtocolDriver<CRE> {

    void initialize();

}
