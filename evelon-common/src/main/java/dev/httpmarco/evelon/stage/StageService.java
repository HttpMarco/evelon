package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.stage.types.ObjectType;
import dev.httpmarco.evelon.stage.types.ParameterType;

import java.util.HashSet;
import java.util.Set;

public final class StageService {

    private final Set<Stage.Type> types;

    public StageService() {
        this.types = new HashSet<>();
        this.types.addAll(Set.of(new ParameterType(), new ObjectType()));
    }

    /**
     * Get the type of the java class
     * @param clazz the class to get the type
     * @return the type of the class
     */
    public Stage.Type typeOf(Class<?> clazz) {
        return types.stream().filter(type -> type.isType(clazz)).findFirst().orElse(null);
    }
}
