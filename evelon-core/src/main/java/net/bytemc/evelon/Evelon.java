package net.bytemc.evelon;

import net.bytemc.evelon.layers.RepositoryLayerHandler;

public class Evelon {

    private static Evelon instance;
    private final RepositoryLayerHandler repositoryLayerHandler;

    public Evelon() {
        instance = this;

        this.repositoryLayerHandler = new RepositoryLayerHandler();
    }

    public static Evelon getInstance() {
        return instance;
    }

    public RepositoryLayerHandler getRepositoryLayerHandler() {
        return repositoryLayerHandler;
    }

}
