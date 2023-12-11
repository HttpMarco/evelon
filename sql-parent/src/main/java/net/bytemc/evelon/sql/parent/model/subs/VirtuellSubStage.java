package net.bytemc.evelon.sql.parent.model.subs;

import net.bytemc.evelon.sql.parent.model.SqlSubStage;

import java.util.List;

public final class VirtuellSubStage implements SqlSubStage<Object> {

    @Override
    public List<String> serialize(String id, Object input) {
        return null;
    }

    @Override
    public boolean isElement(Class<?> type) {
        return true;
    }
}
