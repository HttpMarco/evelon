package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClassImpl;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.osgan.reflections.Reflections;

import java.util.Map;

public abstract class MapSubStage<R extends Builder<R, ?>> implements SubStage<R> {

    @Override
    public void initialize(String stageId, Model<?> model, RepositoryField ownField, RepositoryObjectClass<?> clazz, R queries) {
        var mapTypes = Reflections.of(ownField.field()).generics();

        if(mapTypes.length != 2) {
            throw new IllegalStateException("Map stage type has not exactly two elements. That is not a requirement of maps.");
        }

        var keyType = new RepositoryClassImpl<>(mapTypes[0]);
        var valueType = new RepositoryClassImpl<>(mapTypes[1]);

        var keyStage = model.findStage(keyType.clazz());
        var valueStage = model.findStage(valueType.clazz());

        if(keyStage instanceof ElementStage<?,?,?> elementStage) {

        }


    }

    @Override
    public boolean isElement(Class<?> type) {
        return Map.class.isAssignableFrom(type);
    }
}
