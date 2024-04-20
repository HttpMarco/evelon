package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class HikariDefaultAuthentication extends ConnectionAuthentication {

    private final String hostname;
    private final String database;
    private final String username;
    private final String password;
    private final int port;

    public HikariDefaultAuthentication(String id, boolean active) {
        super(id, active);

        this.username = "root";
        this.database = "database";
        this.hostname = "localhost";
        this.password = "password";
        this.port = 3306;
    }
}
