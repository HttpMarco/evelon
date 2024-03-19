package dev.httpmarco.evelon;

import dev.httpmarco.evelon.layer.LayerService;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Accessors(fluent = true)
public class Evelon {

    // the single instance of evelon
    @Getter @Accessors(fluent = true)
    private static final Evelon instance = new Evelon();

    // main logger of evelon
    public static final Logger LOGGER = LoggerFactory.getLogger(Evelon.class);

    // layer service control all different database layers
    private final LayerService layerService = new LayerService();

}
