package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.credentials.Credentials;
import dev.httpmarco.evelon.layer.ConnectableLayer;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.layer.credentials.AbstractSqlCredentials;
import dev.httpmarco.evelon.sql.parent.layer.process.SqlCreateProcess;
import dev.httpmarco.evelon.sql.parent.layer.process.SqlInitializeProcess;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.Connection;

@Getter
@Accessors(fluent = true)
public abstract class SqlParentLayer extends ConnectableLayer<Connection> {

    private HikariConnection connection;

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
    public void initialize(Repository<?> repository) {
        new SqlInitializeProcess(this.connection.transmitter(), repository.name(), repository).pushInitialize();
    }

    @Override
    public void close() {
        this.connection.close();
    }

    @Override
    public UpdateResponse create(Repository<?> repository, Object value) {
        return new SqlCreateProcess(this.connection.transmitter(), repository.name(), repository).pushCreate(value);
    }
}
