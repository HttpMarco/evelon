package dev.httpmarco.evelon.common.layers.connection;

public interface EvelonLayerConnection<T> {

    void close();

    void connect();

    boolean isConnected();

    T getConnection();

}
