package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;

public interface ProtocolDriverLoader<CRE extends ConnectionAuthentication> extends ProtocolDriver<CRE> {

    void initialize();

}
