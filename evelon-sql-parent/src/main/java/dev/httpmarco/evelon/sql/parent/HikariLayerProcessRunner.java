package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.AbstractLayer;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;

public final class HikariLayerProcessRunner extends ProcessRunner<String> {

    public HikariLayerProcessRunner(Layer layer) {
        super(layer);
    }

    @Override
    protected void update(String query) {
        //todo implement transmitter
        System.err.println(query);
    }
}