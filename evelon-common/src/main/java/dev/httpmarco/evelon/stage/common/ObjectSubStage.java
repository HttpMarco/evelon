package dev.httpmarco.evelon.stage.common;

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
    public void initialize(RepositoryObjectClass<?> clazz, InitializeProcess process) {
        for (var field : clazz.fields()) {
            if (field.type() == Type.OBJECT) {
                // todo sub process
            } else if (field.type() == Type.PARAMETER) {
                process.affectedRows().add(field);
            }
        }
    }
}
