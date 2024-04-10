package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectionCredentials;

public interface ProtocolDriver<CRE extends ConnectionCredentials> {

    String jdbcUrl(CRE credentials);

    @SuppressWarnings("unchecked")
    default String jdbcUrlBinding(ConnectionCredentials credentials) {
        return this.jdbcUrl((CRE) credentials);
    }

}
