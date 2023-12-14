package net.bytemc.evelon.model.subs;

import net.bytemc.evelon.model.SubStage;

public abstract class AbstractVirtuellSubStage<R> implements SubStage<R> {

    @Override
    public boolean isElement(Class<?> type) {
        return true;
    }
}
