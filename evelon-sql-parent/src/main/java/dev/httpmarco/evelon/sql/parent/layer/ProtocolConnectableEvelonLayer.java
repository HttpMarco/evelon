package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.common.layers.ConnectableEvelonLayer;

import java.sql.Connection;

public interface ProtocolConnectableEvelonLayer extends ConnectableEvelonLayer<Object, Connection> {

    ProtocolDriver protocolDriver();

}
