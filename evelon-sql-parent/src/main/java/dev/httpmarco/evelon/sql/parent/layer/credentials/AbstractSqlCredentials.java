package dev.httpmarco.evelon.sql.parent.layer.credentials;

import dev.httpmarco.evelon.credentials.AbstractCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class AbstractSqlCredentials extends AbstractCredentials {

    private final String hostname;
    private final String username;
    private final String password;
    private final String database;
    private final int port;

    public AbstractSqlCredentials(String id, String hostname, String username, String password, String database, int port) {
        super(id, false);
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
    }
}