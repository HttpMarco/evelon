package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.SubStage;
import java.util.Collection;

public abstract class CollectionSubStage<R extends Builder> implements SubStage<R>  {

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
