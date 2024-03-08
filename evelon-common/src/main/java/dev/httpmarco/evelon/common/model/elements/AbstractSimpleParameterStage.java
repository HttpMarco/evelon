package dev.httpmarco.evelon.common.model.elements;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.osgan.utils.Utils;

public abstract class AbstractSimpleParameterStage<B extends Builder<B, ?>> implements ElementStage<Object, Object, B> {

    @Override
    public boolean isElement(RepositoryClass<?> type) {
        return type.clazz().isPrimitive() || Utils.JAVA_ELEMENTS.contains(type.clazz());
    }
}
