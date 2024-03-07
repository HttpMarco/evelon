package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClassImpl;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.osgan.reflections.Reflections;

import java.util.Map;

public abstract class MapSubStage<B extends Builder<B, ?>> implements SubStage<B> {

    @Override
    public void initialize(String stageId, Model<?> model, RepositoryField ownField, RepositoryObjectClass<?> clazz, B queries) {
        var mapTypes = Reflections.of(ownField.field()).generics();

        if (mapTypes.length != 2) {
            throw new IllegalStateException("Map stage type has not exactly two elements. That is not a requirement of maps.");
        }

        // add foreign key linking
        queries.linkPrimaries(ownField.parentClass().asObjectClass().primaryFields());

        var keyType = new RepositoryClassImpl<>(mapTypes[0]);
        var valueType = new RepositoryClassImpl<>(mapTypes[1]);

        this.initializeKey(queries, model.findStage(keyType.clazz()), ownField, keyType.clazz(), clazz);
        this.initializeValue(queries, model.findStage(valueType.clazz()), ownField, valueType.clazz(), clazz);
    }

    @Override
    public void create(String stageId, Model<?> model, RepositoryField ownField, RepositoryObjectClass<?> clazz, B queries) {
        // todo
    }

    public abstract void initializeKey(B Builder, Stage<?> stage, RepositoryField parentField, Class<?> type, RepositoryObjectClass<?> clazz);

    public abstract void initializeValue(B Builder, Stage<?> stage, RepositoryField parentField, Class<?> type, RepositoryObjectClass<?> clazz);

    @Override
    public boolean isElement(Class<?> type) {
        return Map.class.isAssignableFrom(type);
    }
}
