package dev.httpmarco.evelon.document.redis;

import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class RedisAuthentication extends ConnectionAuthentication {

    private final String hostname;
    private final int port;

    private final String password;
    private final boolean useSsl;

    public RedisAuthentication(String id, boolean active, String hostname, int port, String password, boolean useSsl) {
        super(id, active);
        this.hostname = hostname;
        this.port = port;
        this.password = password;
        this.useSsl = useSsl;
    }
}
