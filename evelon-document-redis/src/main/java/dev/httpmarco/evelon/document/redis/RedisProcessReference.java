package dev.httpmarco.evelon.document.redis;

import dev.httpmarco.evelon.document.redis.connection.RedisConnection;
import dev.httpmarco.evelon.process.ProcessReference;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class RedisProcessReference extends ProcessReference<RedisConnection> {

    public RedisProcessReference(RedisConnection connection) {
        super(connection);
    }
}
