package dev.httpmarco.evelon.layer.connection;

import lombok.experimental.UtilityClass;

import java.nio.file.Path;

@UtilityClass
public final class ConnectionAuthenticationPath {

    public static Path CONFIGURATION_PATH = Path.of("evelon-connection-credentials.json");
    private static final String ENV_ID = "evelon.credentials.path";

    static {
        if (System.getenv().containsKey(ENV_ID)) {
            set(System.getenv(ENV_ID));
        }
    }

    public static void set(String id) {
        CONFIGURATION_PATH = Path.of(id);
    }

}
