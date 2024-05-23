package dev.httpmarco.evelon.json.redis;

import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.jetbrains.annotations.NotNull;

// todo change object
public final class RedisConnection implements Connection<StatefulRedisConnection<String, String>, Object> {

    private RedisClient client;
    private StatefulRedisConnection<String, String> connection;

    @Override
    public void connect(ConnectionAuthentication credentials) {

        var auth = ((RedisAuthentication) credentials);

        this.client = RedisClient.create(RedisURI.Builder.redis("localhost")
                .withSsl(auth.useSsl())
                .withHost(auth.hostname())
                .withPort(auth.port())
                .withPassword(auth.password().toCharArray())
                .build());

        connection = client.connect();
    }

    @Override
    public boolean isConnected() {
        return connection != null && connection.isOpen();
    }

    @Override
    public void close() {
        if (connection != null) {
            this.connection.close();
        }
        if(client != null) {
            this.client.shutdown();
        }
    }

    @Override
    public StatefulRedisConnection<String, String> connection() {
        return this.connection;
    }

    @Override
    public void update(Object query) {

    }

    @Override
    public void query(@NotNull Object query) {

    }
}
