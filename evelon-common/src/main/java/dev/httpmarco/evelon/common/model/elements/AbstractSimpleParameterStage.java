package dev.httpmarco.evelon.common.model.elements;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.osgan.utils.Utils;

public abstract class AbstractSimpleParameterStage<B extends Builder<?, ?>, T> implements ElementStage<Object, B, T> {

    @Override
    public boolean isElement(Class<?> type) {
        return type.isPrimitive() || Utils.JAVA_ELEMENTS.contains(type);
    }
}
