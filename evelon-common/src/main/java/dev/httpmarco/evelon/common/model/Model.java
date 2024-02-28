package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.repository.RepositoryField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class Model {

    private List<Stage> stages = new ArrayList<>();

    public Model() {
        this.applyPlatformStages();
    }

    public abstract void applyPlatformStages();

    public Stage findStage(RepositoryField field) {
        return stages.stream().filter(stage -> stage.isElement(field.fieldType())).findFirst().orElse(null);
    }

    public Stage findStage(Class<?> clazz) {
        return stages.stream().filter(stage -> stage.isElement(clazz)).findFirst().orElse(null);
    }
}
