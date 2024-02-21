package dev.httpmarco.evelon.common.credentials;

import dev.httpmarco.evelon.common.layers.ConnectableEvelonLayer;
import dev.httpmarco.osgon.configuration.gson.JsonUtils;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

public final class CredentialsService {

    private static final Path CREDENTIALS_PATH = Path.of("credentials.json");

    private CredentialsConfig credentialsConfig = new CredentialsConfig();

    public CredentialsService() {
        this.update();
    }

    public void addCredentials(ConnectableEvelonLayer<?, ?, ?> layer) {
        this.credentialsConfig.credentials().add(layer.templateCredentials());
        this.update();
    }

    @SneakyThrows
    public void update() {
        // todo fix in osgan
        Files.writeString(CREDENTIALS_PATH, JsonUtils.getPrettyGson().toJson(this.credentialsConfig));
    }
}
