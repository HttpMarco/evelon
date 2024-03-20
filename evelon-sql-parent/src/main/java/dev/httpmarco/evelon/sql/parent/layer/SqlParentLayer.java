package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.credentials.Credentials;
import dev.httpmarco.evelon.layer.ConnectableLayer;
import dev.httpmarco.evelon.sql.parent.layer.credentials.AbstractSqlCredentials;

public abstract class SqlParentLayer extends ConnectableLayer {

    public SqlParentLayer(Credentials templateCredentials) {
        super(templateCredentials);
    }

    public SqlParentLayer(String id) {
        super(new AbstractSqlCredentials(id, "127.0.0.1", "root", "secret"));
    }
}
