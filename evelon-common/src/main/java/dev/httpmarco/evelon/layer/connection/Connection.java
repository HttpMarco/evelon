package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;

public interface Connection<CO> {

    void connect(LayerConnectionCredentials credentials);

    boolean isConnected();

    void close();

    CO connection();

}
