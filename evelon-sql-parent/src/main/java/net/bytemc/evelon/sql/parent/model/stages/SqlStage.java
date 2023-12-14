package net.bytemc.evelon.sql.parent.model.stages;

import net.bytemc.evelon.model.elemets.AbstractSimpleParameterStage;

public abstract class SqlStage extends AbstractSimpleParameterStage {

    abstract String getSqlType(Class<?> clazz);

}
