package net.bytemc.evelon.sql.parent.model.subs;

import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.parent.model.SqlSubStage;

import java.util.ArrayList;
import java.util.List;

public final class VirtuellSubStage extends SqlSubStage<Object> {

    @Override
    public List<String> serialize(String id, Object input, RepositoryClass<Object> clazz) {
        var list = new ArrayList<String>();



        return list;
    }

    @Override
    public boolean isElement(Class<?> type) {
        return true;
    }
}
