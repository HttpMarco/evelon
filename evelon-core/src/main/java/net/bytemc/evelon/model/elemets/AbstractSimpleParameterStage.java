package net.bytemc.evelon.model.elemets;

import net.bytemc.evelon.misc.EvelonReflections;
import net.bytemc.evelon.model.ElementStage;

public abstract class AbstractSimpleParameterStage implements ElementStage<Object> {

    @Override
    public boolean isElement(Class<?> type) {
        return type.isPrimitive() || EvelonReflections.CONSTANT.contains(type);
    }
}
