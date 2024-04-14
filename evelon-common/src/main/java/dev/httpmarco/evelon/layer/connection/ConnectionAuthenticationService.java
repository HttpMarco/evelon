package dev.httpmarco.evelon.layer.connection;

import com.google.gson.*;
import dev.httpmarco.evelon.Evelon;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Set;

public final class ConnectionAuthenticationService {

    private static final Gson CREDENTIALS_GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIGURATION_PATH = Path.of("evelon-connection-credentials.json");

    static {
        createConfigurationFile();
    }

    @SneakyThrows
    private static void createConfigurationFile() {
        // Create if the not exists
        Files.newByteChannel(CONFIGURATION_PATH, Set.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE)).close();
    }

    public static void appendCredentials(ConnectableLayer<?, ?> connectableLayer, ConnectionAuthentication authentication) {
        var elements = readCredentialsContext();
        for (var credentials : elements) {
            if (!credentials.isJsonObject()) {
                continue;
            }
            var credentialsAsJsonObject = credentials.getAsJsonObject();
            if (credentialsAsJsonObject.get("id").getAsString().equalsIgnoreCase(connectableLayer.id())) {
                continue;
            }
            if (!credentialsAsJsonObject.get("active").getAsBoolean()) {
                Evelon.LOGGER.warn("Repository use {}, but session is inactive.", connectableLayer.id());
                return;
            }
            connectableLayer.connection().connect(CREDENTIALS_GSON.fromJson(credentials, authentication.getClass()));
            return;
        }
        elements.add(CREDENTIALS_GSON.toJsonTree(authentication));
        updateCredentialsContext(elements);
    }

    @SneakyThrows
    private static @NotNull JsonArray readCredentialsContext() {
        var authenticationArray = CREDENTIALS_GSON.fromJson(Files.newBufferedReader(CONFIGURATION_PATH), JsonArray.class);
        if (authenticationArray == null) {
            return new JsonArray();
        }
        return authenticationArray;
    }

    @SneakyThrows
    private static void updateCredentialsContext(JsonArray elements) {
        Files.writeString(CONFIGURATION_PATH, CREDENTIALS_GSON.toJson(elements));
    }
}