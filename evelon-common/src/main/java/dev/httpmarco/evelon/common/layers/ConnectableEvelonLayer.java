package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.credentials.Credentials;
import dev.httpmarco.evelon.common.layers.connection.EvelonLayerConnection;

public interface ConnectableEvelonLayer<T, R extends Credentials, C> extends EvelonModelLayer<T> {

    EvelonLayerConnection<R, C> connection();

}
