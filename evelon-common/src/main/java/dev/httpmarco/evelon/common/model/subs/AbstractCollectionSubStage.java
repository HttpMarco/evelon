package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.process.impl.ConstructProcess;
import dev.httpmarco.evelon.common.process.impl.CreateProcess;
import dev.httpmarco.evelon.common.process.impl.InitializeProcess;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClassImpl;
import dev.httpmarco.osgan.reflections.Reflections;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCollectionSubStage implements SubStage<Collection<?>> {

    @Override
    public void initialize(Repository<?> repository, String stageId, Model model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, InitializeProcess queries) {
        var collectionListType = Reflections.of(ownField.field()).generics();

        if (collectionListType.length != 1) {
            throw new IllegalStateException("Collection stage tpe has multiple elements. That is not a requirement of collections.");
        }

        var collectionType = new RepositoryObjectClassImpl<>(repository, collectionListType[0]);

        //todo
        /*
        // add foreign key linking
        queries.linkPrimaries(ownField.parentClass().asObjectClass().primaryFields());

        var stage = collectionType.stageOf(model);
        if (stage.isElementStage()) {
            this.appendElementStage(queries, new RepositoryFieldImpl<>(repository, collectionType, ownField.id(), clazz));
        } else if (stage.isSubStage()) {
            throw new NotImplementedException("Substages are not supported yet.");
            // todo: implementation
        } else throw new RuntimeException("This stage is not supported yet.");

         */
    }

    @Override
    public void create(Collection<?> value, String stageId, Model model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, CreateProcess queries) {
        //todo
    }

    @Override
    public Collection<?> construct(Model model, RepositoryClass<?> clazz, ConstructProcess builder) {
        // todo
        return List.of();
    }

    @Override
    public boolean isElement(Model model, Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    /**
     * Append the element stage to the builder. Here we cant know the type of the element, so we need to pass the class
     *
     * @param builder the builder
     * @param field   the class of the element
     */
    //todo
   // public abstract void appendElementStage(B builder, RepositoryField<?> field);

}