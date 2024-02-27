package dev.httpmarco.evelon.common.credentials;

import dev.httpmarco.evelon.common.layers.ConnectableEvelonLayer;
import dev.httpmarco.osgon.files.json.JsonDocument;

import java.nio.file.Path;

public final class CredentialsService {

    private final JsonDocument<CredentialsConfiguration> config = new JsonDocument<>(new CredentialsConfiguration(), Path.of("credentials.json"), new CredentialsTypeAdapter());

    public void addCredentials(ConnectableEvelonLayer<?, ?, ?> layer) {
        if (config.value().credentials().stream().noneMatch(it -> it.id().equals(layer.templateCredentials().id()))) {
            this.config.append(list -> list.credentials().add(layer.templateCredentials()));
        }
    }

    public boolean isPresent(ConnectableEvelonLayer<?, ?, ?> layer) {
        return config.value().credentials().stream().anyMatch(it -> it.enabled() && it.id().equals(layer.templateCredentials().id()));
    }

    public Credentials credentials(ConnectableEvelonLayer<?, ?, ?> layer) {
        return config.value().credentials().stream().filter(it -> it.id().equals(layer.templateCredentials().id())).findFirst().orElseThrow();
    }
}