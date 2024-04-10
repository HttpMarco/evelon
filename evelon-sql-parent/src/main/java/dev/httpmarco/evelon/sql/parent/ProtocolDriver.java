package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;

public interface ProtocolDriver<CRE extends ConnectionAuthentication> {

    String jdbcUrl(CRE credentials);

    @SuppressWarnings("unchecked")
    default String jdbcUrlBinding(ConnectionAuthentication credentials) {
        return this.jdbcUrl((CRE) credentials);
    }

}
