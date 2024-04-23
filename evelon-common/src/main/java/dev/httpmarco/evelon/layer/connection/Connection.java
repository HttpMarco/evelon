package dev.httpmarco.evelon.layer.connection;

import org.jetbrains.annotations.NotNull;

public interface Connection<C, Q> {

    void connect(ConnectionAuthentication credentials);

    boolean isConnected();

    void close();

    C connection();

    void update(Q query);

    void query(@NotNull Q query);

}
