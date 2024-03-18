package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.stage.types.ObjectType;
import dev.httpmarco.evelon.stage.types.ParameterType;

import java.util.HashSet;
import java.util.Set;

public class StageService {

    private final Set<Stage.Type> types;

    public StageService() {
        this.types = new HashSet<>();
        this.types.addAll(Set.of(new ParameterType(), new ObjectType()));
    }

    public Stage.Type typeOf(Class<?> clazz) {
        return types.stream().filter(type -> type.isType(clazz)).findFirst().orElse(null);
    }
}
