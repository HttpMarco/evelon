package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;

public final class PreppedProcess implements Process {

    @Override
    public void run(Repository<?> repository, Layer layer) {
        var stage = repository.entry().stage(layer);

        if(!stage.isSubStage()) {
            throw new UnsupportedOperationException("Cannot create table for non-substage");
        }

        stage.asSubStage().initialize(repository.entry(), layer);
    }
}
