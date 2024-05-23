package dev.httpmarco.evelon.json.redis;

import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class RedisAuthentication extends ConnectionAuthentication {

    private final String hostname;
    private final int port;
    private final String password;
    private final boolean useSsl;

    public RedisAuthentication() {
        super("REDIS", false);

        this.password = "password";
        this.useSsl = false;
        this.port = 3636;
        this.hostname = "127.0.0.1";
    }
}
