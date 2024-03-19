package dev.httpmarco.evelon.stage.common;

import dev.httpmarco.evelon.stage.SubStage;
import dev.httpmarco.evelon.stage.Type;

public final class ObjectSubStage implements SubStage {
    @Override
    public Type type() {
        return Type.OBJECT;
    }
}
