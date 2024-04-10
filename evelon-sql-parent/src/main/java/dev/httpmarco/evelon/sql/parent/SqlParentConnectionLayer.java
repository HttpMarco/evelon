package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class SqlParentConnectionLayer<CQ extends LayerConnectionCredentials> implements ConnectableLayer<CQ, Void> {

    private String id;
    private CQ templateCredentials;

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public Void connection() {
        return null;
    }

    @Override
    public void connect(CQ credentials) {

    }

    @Override
    public CQ templateCredentials() {
        return this.templateCredentials;
    }

    @Override
    public void close() {

    }
}
