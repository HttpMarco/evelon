package dev.httpmarco.evelon.layer.connection;

public interface Connection<C> {

    void connect(ConnectionAuthentication credentials);

    boolean isConnected();

    void close();

    C connection();

    void update(String query);

}
