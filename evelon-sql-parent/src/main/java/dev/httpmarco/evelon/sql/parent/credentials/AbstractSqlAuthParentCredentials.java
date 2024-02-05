package dev.httpmarco.evelon.sql.parent.credentials;

import dev.httpmarco.evelon.common.credentials.AbstractCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class AbstractSqlAuthParentCredentials extends AbstractCredentials {

    private final String hostname;
    private final String username;
    private final String password;

    public AbstractSqlAuthParentCredentials(String id, String hostname, String username, String password) {
        super(id);
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }
}
