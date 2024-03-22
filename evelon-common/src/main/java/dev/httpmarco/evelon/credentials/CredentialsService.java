package dev.httpmarco.evelon.credentials;

import dev.httpmarco.evelon.layer.ConnectableLayer;
import dev.httpmarco.osgan.files.json.JsonDocument;

import java.nio.file.Path;

public class CredentialsService {

    private final JsonDocument<CredentialsConfiguration> config = new JsonDocument<>(new CredentialsConfiguration(),
            Path.of("credentials.json"),
            new CredentialsTypeAdapter());

    public void addCredentials(ConnectableLayer<?> layer) {
        if (config.value().credentials().stream().noneMatch(it -> it.id().equals(layer.templateCredentials().id()))) {
            this.config.append(list -> list.credentials().add(layer.templateCredentials()));
        }
    }

    public boolean isPresent(ConnectableLayer<?> layer) {
        return config.value().credentials().stream().anyMatch(it -> it.enabled() && it.id().equals(layer.templateCredentials().id()));
    }

    public Credentials credentials(ConnectableLayer<?> layer) {
        return config.value().credentials().stream().filter(it -> it.id().equals(layer.templateCredentials().id())).findFirst().orElseThrow();
    }
}
