package dev.httpmarco.evelon.common.credentials;

import dev.httpmarco.evelon.common.layers.EvelonLayer;

import java.util.ArrayList;
import java.util.List;

public class CredentialsConfig {

    private final List<Credentials> credentials = new ArrayList<>();

    public Credentials credentials(EvelonLayer<?> evelonLayer) {
        return credentials.stream().filter(it -> it.id().equals(evelonLayer.id())).findFirst().orElseThrow();
    }
}
