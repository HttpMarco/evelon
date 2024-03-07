package dev.httpmarco.evelon.common;

import dev.httpmarco.evelon.common.credentials.CredentialsService;
import dev.httpmarco.evelon.common.layers.EvelonLayerPool;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Accessors(fluent = true)
public class Evelon {

    public static final Logger LOGGER = LoggerFactory.getLogger(Evelon.class);

    @Getter
    private final static Evelon instance = new Evelon();
    private final CredentialsService credentialsService = new CredentialsService();
    private final EvelonLayerPool layerPool = new EvelonLayerPool();

}
