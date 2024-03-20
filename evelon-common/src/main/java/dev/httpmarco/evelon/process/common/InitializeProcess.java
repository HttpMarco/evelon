package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.stage.SubStage;
import dev.httpmarco.evelon.stage.Type;
import dev.httpmarco.osgan.utils.validate.Check;

public class InitializeProcess extends Process {

    public InitializeProcess(String id, Repository<?> repository) {
        super(id, repository);
    }

    @Override
    public void run() {
        var repository = meta().repository();
        var type = repository.clazz().type();

        Check.stateCondition(type != Type.OBJECT, "The repository type is not an object");

        for (Layer layer : repository.layers()) {
            // initialize the layer
            ((SubStage) layer.stage(type)).initialize((RepositoryObjectClass<?>) repository.clazz(), this);
        }
    }
}
