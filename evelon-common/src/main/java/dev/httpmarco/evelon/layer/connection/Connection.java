package dev.httpmarco.evelon.layer.connection;

public interface Connection<C, Q> {

    void connect(ConnectionAuthentication credentials);

    boolean isConnected();

    void close();

    C connection();

    void update(Q query);

}
