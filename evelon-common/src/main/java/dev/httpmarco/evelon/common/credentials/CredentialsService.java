package dev.httpmarco.evelon.common.credentials;

import dev.httpmarco.evelon.common.layers.ConnectableEvelonLayer;
import dev.httpmarco.osgon.files.json.JsonDocument;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class CredentialsService {

    private final JsonDocument<List<Credentials>> config = new JsonDocument<>(new ArrayList<>(), Path.of("credentials.json"));

    public void addCredentials(ConnectableEvelonLayer<?, ?, ?> layer) {
        this.config.append(list -> list.add(layer.templateCredentials()));
    }
}
