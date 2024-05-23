package dev.httpmarco.evelon.json.redis;

import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import io.lettuce.core.RedisClient;
import org.jetbrains.annotations.NotNull;

// todo change object
public final class RedisConnection implements Connection<RedisClient, Object> {

    private RedisClient client;

    @Override
    public void connect(ConnectionAuthentication credentials) {
        //todo add crendetials
        client = RedisClient.create("redis://localhost:6379");
    }

    @Override
    public boolean isConnected() {
        return client != null && client.connect().isOpen();
    }

    @Override
    public void close() {
        client.shutdown();
    }

    @Override
    public RedisClient connection() {
        return client;
    }

    @Override
    public void update(Object query) {

    }

    @Override
    public void query(@NotNull Object query) {

    }
}
