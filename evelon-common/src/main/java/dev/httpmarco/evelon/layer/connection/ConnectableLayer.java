package dev.httpmarco.evelon.layer.connection;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;

public interface ConnectableLayer<C extends LayerConnectionCredentials, CQ> extends Layer {

    void connect(C credentials);

    boolean isConnected();

    void close();

    CQ connection();

    C templateCredentials();

}
