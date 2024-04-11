package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;
import org.jetbrains.annotations.NotNull;

public final class PreppedProcess implements Process {

    @Override
    public void run(@NotNull Repository<?> repository, Layer layer) {
        var stage = repository.entry().stage(layer);

        if(!stage.isSubStage()) {
            throw new UnsupportedOperationException("Cannot create table for non-substage");
        }

        stage.asSubStage().initialize(repository.entry(), layer);
    }
}
