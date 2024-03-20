package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.credentials.Credentials;

public interface LayerConnection<R extends Credentials, T> {

    void close();

    void connect(R credentials);

    @SuppressWarnings("unchecked")
    default void connectWithMapping(Object credentials) {
        this.connect((R) credentials);
    }

    boolean isConnected();

    T getConnection();

}