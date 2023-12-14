package net.bytemc.evelon.sql.parent.model.stages;

import net.bytemc.evelon.model.elemets.AbstractSimpleParameterStage;

public final class SimpleParameterStage extends AbstractSimpleParameterStage {

    @Override
    public Object serializeElement(Object element) {
        return "'" + element.toString() + "'";
    }
}
