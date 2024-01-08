package dev.httpmarco.evelon.common.model.elements;

import dev.httpmarco.evelon.common.misc.Reflections;
import dev.httpmarco.evelon.common.model.ElementStage;

public abstract class AbstractSimpleParameterStage implements ElementStage<Object> {

    @Override
    public boolean isElement(Class<?> type) {
        return type.isPrimitive() || Reflections.CONSTANT.contains(type);
    }
}
