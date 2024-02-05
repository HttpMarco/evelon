package dev.httpmarco.evelon.common;

import dev.httpmarco.evelon.common.credentials.CredentialsConfig;
import dev.httpmarco.evelon.common.layers.EvelonLayerPool;
import dev.httpmarco.osgon.configuration.ConfigHelper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Evelon {

    private static Evelon instance;

    private final EvelonLayerPool layerPool = new EvelonLayerPool();

    private final CredentialsConfig credentialsConfig;

    public Evelon() {
        credentialsConfig = ConfigHelper.getConfigOrCreate("credentials.json", CredentialsConfig.class, new CredentialsConfig());
    }

    public static Evelon getInstance() {
        if (instance == null) {
            return instance = new Evelon();
        }
        return instance;
    }
}
