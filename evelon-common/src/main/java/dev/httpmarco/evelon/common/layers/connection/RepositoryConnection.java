package dev.httpmarco.evelon.common.layers.connection;

public interface RepositoryConnection<T> {

    void close();

    void connect();

    boolean isConnected();

    T getConnection();

}
