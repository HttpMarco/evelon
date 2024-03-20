package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.credentials.AbstractCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class H2Credentials extends AbstractCredentials {

    private final String path;

    public H2Credentials() {
        super("H2");
        this.path = "database.h2";
    }
}
