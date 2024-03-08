package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class Model<B extends Builder<B, ?>> {

    private final List<Stage<?, B>> stages = new ArrayList<>();

    public Model() {
        this.applyPlatformStages();
    }

    public abstract void applyPlatformStages();

    @SuppressWarnings("unchecked")
    public <T> Stage<T, B> findStage(RepositoryClass<T> clazz) {
        return (Stage<T, B>) stages.stream().filter(stage -> stage.isElement(clazz)).findFirst().orElseThrow();
    }
}
