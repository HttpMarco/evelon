package net.bytemc.evelon.sql.transformers;

import net.bytemc.evelon.sql.ElementStageTransformer;
import net.bytemc.evelon.sql.Stage;
import net.bytemc.evelon.sql.StageHandler;
import net.bytemc.evelon.sql.substages.VirtualObjectStage;

public final class RecordTransformer implements ElementStageTransformer {

    @Override
    public Stage<?> transformTo() {
        return StageHandler.getInstance().getStage(VirtualObjectStage.class);
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.isRecord();
    }

}
