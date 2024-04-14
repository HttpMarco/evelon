package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;

public final class HikariConnectionRunner extends ProcessRunner<String> {

    public HikariConnectionRunner(Layer<String> layer) {
        super(layer);
    }

    @Override
    public void update(String query) {
        System.err.println("Update: " + query);
    }

}
