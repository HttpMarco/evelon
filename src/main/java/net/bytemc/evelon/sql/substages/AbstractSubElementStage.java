package net.bytemc.evelon.sql.substages;

import lombok.Getter;
import net.bytemc.evelon.sql.StageHandler;
import net.bytemc.evelon.sql.SubElementStage;

abstract class AbstractSubElementStage<T> implements SubElementStage<T> {

    static final String VALUE_ID = "_value";

    @Getter
    private StageHandler stageHandler;

    public AbstractSubElementStage() {
        this.stageHandler = StageHandler.getInstance();
    }
}