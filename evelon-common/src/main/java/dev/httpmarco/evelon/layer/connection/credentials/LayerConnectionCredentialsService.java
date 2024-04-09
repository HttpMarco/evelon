package dev.httpmarco.evelon.layer.connection.credentials;

import com.google.gson.*;
import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Set;

public final class LayerConnectionCredentialsService {

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

    @SuppressWarnings("unchecked")
    public static <C extends LayerConnectionCredentials> void appendCredentials(ConnectableLayer<C, ?> connectableLayer) {
        for (var credentials : readCredentialsContext()) {
            if (!credentials.isJsonObject()) {
                continue;
            }
            var credentialsAsJsonObject = credentials.getAsJsonObject();
            if (credentialsAsJsonObject.get("id").getAsString().equals(connectableLayer.id())) {
                if(!credentialsAsJsonObject.get("active").getAsBoolean()) {
                    continue;
                }
                connectableLayer.connect((C) CREDENTIALS_GSON.fromJson(credentials, connectableLayer.templateCredentials().getClass()));
                return;
            }
        }
        Evelon.LOGGER.warn("Cannot open layer " + connectableLayer.id() + ", because credentials are missing!");
    }

    @SneakyThrows
    private static JsonArray readCredentialsContext() {
        return CREDENTIALS_GSON.fromJson(Files.newBufferedReader(CONFIGURATION_PATH), JsonArray.class);
    }
}