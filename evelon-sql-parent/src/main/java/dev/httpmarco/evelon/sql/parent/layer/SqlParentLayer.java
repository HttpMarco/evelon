package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.credentials.Credentials;
import dev.httpmarco.evelon.layer.ConnectableLayer;
import dev.httpmarco.evelon.layer.LayerConnection;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.layer.credentials.AbstractSqlCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.Connection;

@Getter
@Accessors(fluent = true)
public abstract class SqlParentLayer extends ConnectableLayer<Connection> {

    private LayerConnection<Connection> connection;

    public SqlParentLayer(ProtocolDriver<? extends Credentials> driver, Credentials templateCredentials) {
        super(templateCredentials);
        this.connection = new HikariConnection(driver);
    }

    public SqlParentLayer(String id) {
        super(new AbstractSqlCredentials(id, "127.0.0.1", "root", "secret"));
    }

    @Override
    public void initialize() {
        active(true);
        this.connection.connect(Evelon.instance().credentialsService().credentials(this));
    }

    @Override
    public void close() {
        this.connection.close();
    }
}
