package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.layer.connection.ConnectionCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class H2ConnectionCredentials extends ConnectionCredentials {

    private final String path;

    public H2ConnectionCredentials() {
        super("H2", false);

        this.path = "database.h2";
    }
}
