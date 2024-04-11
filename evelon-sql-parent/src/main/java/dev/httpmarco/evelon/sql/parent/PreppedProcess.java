package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;

public final class PreppedProcess implements Process {

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS %S();";

    @Override
    public void run(Repository<?> repository, Layer layer) {
        var stage = repository.entry().stage(layer);

        if(!stage.isSubStage()) {
            throw new UnsupportedOperationException("Cannot create table for non-substage");
        }

        System.err.println(CREATE_TABLE_QUERY + stage.asSubStage().getClass().getName());
    }
}
