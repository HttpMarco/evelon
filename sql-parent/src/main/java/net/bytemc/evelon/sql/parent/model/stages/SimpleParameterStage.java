package net.bytemc.evelon.sql.parent.model.stages;

import net.bytemc.evelon.misc.EvelonReflections;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.parent.model.SqlStage;

public final class SimpleParameterStage implements SqlStage<Object> {

    @Override
    public String serialize(String id, Object input, RepositoryClass<Object> repositoryClass) {
        return "'" + input.toString() + "'";
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.isPrimitive() || EvelonReflections.CONSTANT.contains(type);
    }
}
