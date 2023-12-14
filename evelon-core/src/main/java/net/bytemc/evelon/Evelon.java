package net.bytemc.evelon;

import lombok.Getter;
import net.bytemc.evelon.layers.RepositoryLayerHandler;

@Getter
public class Evelon {

    @Getter
    private static Evelon instance;
    private final RepositoryLayerHandler repositoryLayerHandler;

    public Evelon() {
        instance = this;

        this.repositoryLayerHandler = new RepositoryLayerHandler();
    }
}
