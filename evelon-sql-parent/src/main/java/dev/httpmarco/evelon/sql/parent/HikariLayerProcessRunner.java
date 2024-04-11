package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.process.ProcessRunner;

public final class HikariLayerProcessRunner extends ProcessRunner<String> {

    @Override
    protected void update(String query) {
        //todo implement transmitter
        System.err.println(query);
    }
}