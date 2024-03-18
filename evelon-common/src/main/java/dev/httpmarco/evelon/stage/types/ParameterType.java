package dev.httpmarco.evelon.stage.types;

import dev.httpmarco.evelon.stage.Stage;
import dev.httpmarco.osgan.utils.Utils;
import org.jetbrains.annotations.NotNull;

/**
 * This class is a stage type that is used to represent a type that is a parameter
 * or a primitive type.
 */
public class ParameterType extends Stage.Type {

    @Override
    public boolean isType(@NotNull Class<?> clazz) {
        return clazz.isPrimitive() || Utils.JAVA_ELEMENTS.contains(clazz);
    }
}