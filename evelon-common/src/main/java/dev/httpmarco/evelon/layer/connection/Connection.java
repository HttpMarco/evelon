package dev.httpmarco.evelon.layer.connection;

public interface Connection<CON> {

    void connect(ConnectionCredentials credentials);

    boolean isConnected();

    void close();

    CON connection();

}
