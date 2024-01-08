package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.model.SubStage;

public abstract class AbstractVirtuellSubStage<R> implements SubStage<R> {

    @Override
    public boolean isElement(Class<?> type) {
        return true;
    }
}
