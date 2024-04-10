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

    @SneakyThrows
    public static <C extends LayerConnectionCredentials> void appendCredentials(ConnectableLayer<C, ?> connectableLayer) {
        var elements = readCredentialsContext();
        if(elements == null) {
            elements = new JsonArray();
        }

        for (var credentials : elements) {
            if (!credentials.isJsonObject()) {
                continue;
            }
            var credentialsAsJsonObject = credentials.getAsJsonObject();
            System.err.println(credentialsAsJsonObject.get("id").getAsString());
            if (credentialsAsJsonObject.get("id").getAsString().equalsIgnoreCase(connectableLayer.id())) {
                if (!credentialsAsJsonObject.get("active").getAsBoolean()) {
                    return;
                }
                connectableLayer.connection().connect(CREDENTIALS_GSON.fromJson(credentials, connectableLayer.templateCredentials().getClass()));
                return;
            }
        }
        elements.add(CREDENTIALS_GSON.toJsonTree(connectableLayer.templateCredentials()));
        Files.writeString(CONFIGURATION_PATH, CREDENTIALS_GSON.toJson(elements));
    }

    @SneakyThrows
    private static JsonArray readCredentialsContext() {
        return CREDENTIALS_GSON.fromJson(Files.newBufferedReader(CONFIGURATION_PATH), JsonArray.class);
    }
}