package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.process.impl.ConstructProcess;
import dev.httpmarco.evelon.common.process.impl.CreateProcess;
import dev.httpmarco.evelon.common.process.impl.InitializeProcess;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClassImpl;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.osgan.reflections.Reflections;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class AbstractMapSubStage implements SubStage<Map<?, ?>> {

    @Override
    public void initialize(Repository<?> repository, String stageId, Model model, @NotNull RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, InitializeProcess queries) {
        var mapTypes = Reflections.of(ownField.field()).generics();

        if (mapTypes.length != 2) {
            throw new IllegalStateException("Map stage type has not exactly two elements. That is not a requirement of maps.");
        }

        // add foreign key linking
        //todo
        // queries.linkPrimaries(ownField.parentClass().asObjectClass().primaryFields());

        var keyType = new RepositoryClassImpl<>(mapTypes[0]);
        var valueType = new RepositoryClassImpl<>(mapTypes[1]);

        this.initializeMapElement(repository, true, queries, keyType.stageOf(model), ownField, keyType, clazz);
        this.initializeMapElement(repository, false, queries, valueType.stageOf(model), ownField, valueType, clazz);
    }

    @Override
    public void create(Map<?, ?> value, String stageId, Model model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, CreateProcess queries) {
        // todo
    }

    public abstract void initializeMapElement(Repository<?> repository, boolean key, InitializeProcess process, Stage<?> stage, RepositoryField<?> parentField, RepositoryClass<?> type, RepositoryObjectClass<?> clazz);

    @Override
    public boolean isElement(Model model, Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    @Override
    public Map<?, ?> construct(Model model, RepositoryClass<?> clazz, ConstructProcess builder) {
        // todo
        return Map.of();
    }
}
