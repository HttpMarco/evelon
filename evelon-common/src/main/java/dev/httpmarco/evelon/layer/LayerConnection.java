package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.credentials.Credentials;

public interface LayerConnection<T> {

    void close();

    void connect(Credentials credentials);

    boolean isConnected();

    T getConnection();

}