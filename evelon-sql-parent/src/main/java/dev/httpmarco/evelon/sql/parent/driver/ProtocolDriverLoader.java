package dev.httpmarco.evelon.sql.parent.driver;

import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;

public interface ProtocolDriverLoader<CRE extends ConnectionAuthentication> extends ProtocolDriver<CRE> {

    void initialize();

}
