package dev.httpmarco.evelon.common.model.elements;

import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.osgan.utils.Utils;

public abstract class AbstractSimpleParameterStage<B, T> implements ElementStage<Object, B, T> {

    @Override
    public boolean isElement(Class<?> type) {
        return type.isPrimitive() || Utils.JAVA_ELEMENTS.contains(type);
    }
}
