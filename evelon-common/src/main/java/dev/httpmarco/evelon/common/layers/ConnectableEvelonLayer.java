package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.layers.connection.EvelonLayerConnection;

public interface ConnectableEvelonLayer<T, R> extends EvelonLayer<T> {

    void preConnection();

    EvelonLayerConnection<R> connection();

    void postConnection();

}
