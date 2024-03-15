package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.process.Build;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class Model {

    private final List<Stage<?>> stages = new ArrayList<>();

    public Model() {
        this.applyPlatformStages();
    }

    public abstract void applyPlatformStages();

    @SuppressWarnings("unchecked")
    public <T> Stage<T> findStage(Class<T> clazz) {
        return (Stage<T>) stages.stream().filter(stage -> stage.isElement(this, clazz)).findFirst().orElseThrow();
    }
}
