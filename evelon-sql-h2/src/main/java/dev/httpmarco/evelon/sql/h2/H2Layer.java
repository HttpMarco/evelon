package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;

public class H2Layer implements ConnectableLayer<LayerConnectionCredentials, Object> {

    @Override
    public String id() {
        return "H2";
    }


    @Override
    public void connect(LayerConnectionCredentials credentials) {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public Object connection() {
        return null;
    }

    @Override
    public LayerConnectionCredentials templateCredentials() {
        return null;
    }
}
