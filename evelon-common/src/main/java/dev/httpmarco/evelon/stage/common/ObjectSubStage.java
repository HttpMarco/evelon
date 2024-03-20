package dev.httpmarco.evelon.stage.common;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.stage.SubStage;
import dev.httpmarco.evelon.stage.Type;

public final class ObjectSubStage implements SubStage {

    @Override
    public Type type() {
        return Type.OBJECT;
    }

    @Override
    public void initialize(Layer layer, RepositoryObjectClass<?> clazz, InitializeProcess process) {
        for (var field : clazz.fields()) {
            if (field.type() == Type.OBJECT) {
                // todo: implement
                //   ((SubStage) layer.stage(field.type())).initialize(layer, (RepositoryObjectClass<?>) field, process.subProcess(field.id());
                //   field.type()
            } else if (field.type() == Type.PARAMETER) {
                process.affectedRows().add(field);
            }
        }
    }
}
