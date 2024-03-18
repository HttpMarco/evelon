package dev.httpmarco.evelon.stage.types;

import dev.httpmarco.evelon.stage.Stage;
import dev.httpmarco.osgan.utils.Utils;

public class ParameterType extends Stage.Type {

    @Override
    public boolean isType(Class<?> clazz) {
        return clazz.isPrimitive() || Utils.JAVA_ELEMENTS.contains(clazz);
    }
}
