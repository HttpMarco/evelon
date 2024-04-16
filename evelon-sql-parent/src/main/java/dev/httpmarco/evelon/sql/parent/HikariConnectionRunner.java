package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;

public final class HikariConnectionRunner extends ProcessRunner<String> {

    private final HikariConnection connection;

    public HikariConnectionRunner(Layer<String> layer, HikariConnection connection) {
        super(layer);
        this.connection = connection;
    }

    @Override
    public void update(String query, Object... arguments) {
        this.connection.update(query, arguments);
    }
}
