package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;

public interface Connection<CON> {

    void connect(LayerConnectionCredentials credentials);

    boolean isConnected();

    void close();

    CON connection();

}
