package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class Model<B extends Builder<?, ?>> {

    private final List<Stage<B>> stages = new ArrayList<>();

    public Model() {
        this.applyPlatformStages();
    }

    public abstract void applyPlatformStages();

    public Stage<B> findStage(RepositoryField field) {
        return stages.stream().filter(stage -> stage.isElement(field.fieldType())).findFirst().orElseThrow();
    }

    public Stage<B> findStage(Class<?> clazz) {
        return stages.stream().filter(stage -> stage.isElement(clazz)).findFirst().orElseThrow();
    }
}
