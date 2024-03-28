package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.credentials.Credentials;
import dev.httpmarco.evelon.layer.ConnectableLayer;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.layer.filtering.SqlFilterHandler;
import dev.httpmarco.evelon.sql.parent.layer.process.common.*;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.Connection;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class SqlParentLayer extends ConnectableLayer<Connection> {

    private final HikariConnection connection;

    public SqlParentLayer(ProtocolDriver<? extends Credentials> driver, Credentials templateCredentials) {
        super(new SqlFilterHandler(), templateCredentials);
        this.connection = new HikariConnection(driver);
    }

    @Override
    public void initialize() {
        active(true);
        this.connection.connect(Evelon.instance().credentialsService().credentials(this));
    }

    @Override
    public <T> void initialize(Query<T> query) {
        new SqlInitializeProcess<>(this.connection.transmitter(), query.repository().name(), query).pushInitialize();
    }

    @Override
    public void close() {
        this.connection.close();
    }

    @Override
    public <T> UpdateResponse create(Query<T> query, Object value) {
        return new SqlCreateProcess<>(query.repository().name(), query, this.connection.transmitter()).pushCreation(value);
    }

    @Override
    public <T> UpdateResponse deleteAll(Query<T> query) {
        return new SqlDeleteProcess<>(query.repository().name(), query, this.connection.transmitter()).pushDeletion();
    }

    @Override
    public <T> QueryResponse<List<T>> findAll(Query<T> query) {
        return new SqlConstructProcess<>(this.connection.transmitter(), query, -1).queryConstruct(this);
    }

    @Override
    public <T> QueryResponse<Boolean> exists(Query<T> query) {
        return new SqlExistsProcess<>(query.repository().name(), query, this.connection.transmitter()).queryExists();
    }

    @Override
    public <T> UpdateResponse update(Query<T> query, T value) {
        return new SqlUpdateProcess<>(query.repository().name(), query, this.connection.transmitter()).pushUpdate(value, this);
    }
}
