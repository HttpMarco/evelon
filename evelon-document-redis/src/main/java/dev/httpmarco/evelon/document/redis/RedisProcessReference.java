package dev.httpmarco.evelon.document.redis;

import dev.httpmarco.evelon.document.redis.connection.RedisConnection;
import dev.httpmarco.evelon.process.ProcessReference;

public final class RedisProcessReference extends ProcessReference<RedisConnection> {

    public RedisProcessReference(RedisConnection connection) {
        super(connection);
    }
}
