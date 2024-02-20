package dev.httpmarco.evelon.common;

import dev.httpmarco.evelon.common.credentials.CredentialsService;
import dev.httpmarco.evelon.common.layers.EvelonLayerPool;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Evelon {

    @Getter
    private final static Evelon instance = new Evelon();
    private final CredentialsService credentialsService = new CredentialsService();
    private final EvelonLayerPool layerPool = new EvelonLayerPool();

}
