package dev.httpmarco.evelon.common.layers.connection;

import dev.httpmarco.evelon.common.credentials.Credentials;

public interface EvelonLayerConnection<R extends Credentials, T> {

    void close();

    void connect(R credentials);

    boolean isConnected();

    T getConnection();

}
