package dev.httpmarco.evelon.stage.common;

import dev.httpmarco.evelon.stage.Stage;
import dev.httpmarco.evelon.stage.Type;

public final class ParameterStage implements Stage {

    @Override
    public Type type() {
        return Type.PARAMETER;
    }
}
