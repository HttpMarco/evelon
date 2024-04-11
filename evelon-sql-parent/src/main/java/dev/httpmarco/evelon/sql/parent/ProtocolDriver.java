package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;

public interface ProtocolDriver<A extends ConnectionAuthentication> {

    String jdbcUrl(A credentials);

    @SuppressWarnings("unchecked")
    default String jdbcUrlBinding(ConnectionAuthentication credentials) {
        return this.jdbcUrl((A) credentials);
    }

}
