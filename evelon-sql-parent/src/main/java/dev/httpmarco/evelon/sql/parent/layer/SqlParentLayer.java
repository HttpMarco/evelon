package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.repository.Repository;

public abstract class SqlParentLayer implements Layer {

    @Override
    public void initialize(Repository<?> repository, InitializeProcess process) {
        // todo create and load table
    }
}
