package net.bytemc.evelon.sql.parent.model.stages;

import net.bytemc.evelon.misc.EvelonReflections;
import net.bytemc.evelon.sql.parent.model.SqlStage;

public class SimpleParameterStage implements SqlStage<Object> {

    @Override
    public String serialize(String id, Object input) {
        return "'" + input.toString() + "'";
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.isPrimitive() || EvelonReflections.CONSTANT.contains(type);
    }
}
