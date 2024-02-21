package dev.httpmarco.evelon.h2.credentials;

import dev.httpmarco.evelon.common.credentials.AbstractCredentials;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class H2SqlCredentials extends AbstractCredentials {

    private final String path;

    public H2SqlCredentials() {
        super("H2");
        this.path = "database.h2";
    }
}
