package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.stage.SubStage;
import dev.httpmarco.evelon.stage.Type;
import dev.httpmarco.osgan.utils.validate.Check;

public abstract class InitializeProcess extends Process<InitializeProcess> {

    public InitializeProcess(String id, Repository<?> repository) {
        super(id, repository);
    }

    @Override
    public void run() {
        var repository = meta().repository();
        var type = repository.clazz().type();

        Check.stateCondition(type != Type.OBJECT, "The repository type is not an object type: " + type);

        for (Layer layer : repository.layers()) {
            // initialize the layer
            ((SubStage) layer.stage(type)).initialize(layer, (RepositoryObjectClass<?>) repository.clazz(), this);
        }
    }

    public abstract void pushInitialize();

}