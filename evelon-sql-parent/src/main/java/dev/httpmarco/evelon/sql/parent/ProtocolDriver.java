package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;

public interface ProtocolDriver<CRE extends LayerConnectionCredentials> {

    String jdbcUrl(CRE credentials);

    @SuppressWarnings("unchecked")
    default String jdbcUrlBinding(LayerConnectionCredentials credentials) {
        return this.jdbcUrl((CRE) credentials);
    }

}
