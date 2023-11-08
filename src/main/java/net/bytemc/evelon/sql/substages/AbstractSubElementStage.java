package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.sql.StageHandler;
import net.bytemc.evelon.sql.SubElementStage;

abstract class AbstractSubElementStage<T> implements SubElementStage<T> {

    static final String VALUE_ID = "_value";

    public StageHandler getStageHandler() {
        return  StageHandler.getInstance();
    }
}