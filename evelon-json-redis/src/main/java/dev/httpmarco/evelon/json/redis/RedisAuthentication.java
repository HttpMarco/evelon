package dev.httpmarco.evelon.json.redis;

import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;

public final class RedisAuthentication extends ConnectionAuthentication {

    public RedisAuthentication() {
        super("REDIS", false);
    }
}
