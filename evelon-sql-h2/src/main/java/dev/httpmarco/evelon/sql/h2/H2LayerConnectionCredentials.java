package dev.httpmarco.evelon.sql.h2;

import dev.httpmarco.evelon.layer.connection.credentials.LayerConnectionCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class H2LayerConnectionCredentials extends LayerConnectionCredentials {

    private final String path;

    public H2LayerConnectionCredentials() {
        super("H2", false);

        this.path = "database.h2";
    }
}
