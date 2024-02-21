package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.common.credentials.Credentials;

public interface ProtocolDriver<D extends Credentials> {

    void onInitialize();

    String jdbcString(D credentials);

    @SuppressWarnings("unchecked")
    default String jdbcStringWithMapping(Credentials credentials) {
        return this.jdbcString((D) credentials);
    }

}
