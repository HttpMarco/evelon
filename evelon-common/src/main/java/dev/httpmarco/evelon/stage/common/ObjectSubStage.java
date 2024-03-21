package dev.httpmarco.evelon.stage.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.stage.SubStage;
import dev.httpmarco.evelon.stage.Type;

public final class ObjectSubStage implements SubStage {

    @Override
    public Type type() {
        return Type.OBJECT;
    }

    @Override
    public void attachAffectedRows(Process process, RepositoryObjectClass<?> clazz) {
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
