package net.bytemc.evelon.model.subs;

import net.bytemc.evelon.layers.RepositoryLayer;
import net.bytemc.evelon.model.ElementStage;
import net.bytemc.evelon.model.SubStage;
import net.bytemc.evelon.repository.RepositoryClass;

import java.util.ArrayList;
import java.util.List;

public class AbstractVirtuellSubStage<T> implements SubStage<List<T>, Object> {

    private final RepositoryLayer layer;

    public AbstractVirtuellSubStage(RepositoryLayer layer) {
        this.layer = layer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> serialize(String id, Object input, RepositoryClass<Object> repositoryClass) {
        var list = new ArrayList<T>();
        for (var field : repositoryClass.getFields()) {
            var stage = field.getStage(layer);
            if (stage instanceof SubStage<?, ?> subStage) {
                list.add((T) subStage.serializeCommon(id + "_" + field.getName(), field.getValue(input), repositoryClass.subClass(field)));
            } else if (stage instanceof ElementStage<?, ?> elementStage) {
                //TODO
            } else {
                //TODO THROW
            }
        }
        return list;
    }

    @Override
    public boolean isElement(Class<?> type) {
        return true;
    }
}
