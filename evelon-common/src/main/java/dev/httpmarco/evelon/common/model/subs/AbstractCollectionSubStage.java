package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClassImpl;
import dev.httpmarco.evelon.common.repository.field.RepositoryFieldImpl;
import dev.httpmarco.osgan.reflections.Reflections;
import dev.httpmarco.osgan.utils.exceptions.NotImplementedException;

import java.util.Collection;

public abstract class AbstractCollectionSubStage<B extends Builder<B, ?>> implements SubStage<Collection<?>, B> {

    @Override
    public void initialize(Repository<?> repository, String stageId, Model<B> model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, B queries) {
        var collectionListType = Reflections.of(ownField.field()).generics();

        if (collectionListType.length != 1) {
            throw new IllegalStateException("Collection stage tpe has multiple elements. That is not a requirement of collections.");
        }

        var collectionType = new RepositoryObjectClassImpl<>(repository, collectionListType[0]);

        // add foreign key linking
        queries.linkPrimaries(ownField.parentClass().asObjectClass().primaryFields());

        var stage = collectionType.stageOf(model);
        if (stage.isElementStage()) {
            this.appendElementStage(queries, new RepositoryFieldImpl(repository, collectionType, ownField.id(), clazz));
        } else if (stage.isSubStage()) {
            throw new NotImplementedException("Substages are not supported yet.");
            // todo: implementation
        } else throw new RuntimeException("This stage is not supported yet.");
    }

    @Override
    public void create(Collection<?> value, String stageId, Model<B> model, RepositoryField<Collection<?>> ownField, RepositoryObjectClass<?> clazz, B queries) {
        //todo
    }

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    /**
     * Append the element stage to the builder. Here we cant know the type of the element, so we need to pass the class
     *
     * @param builder the builder
     * @param field   the class of the element
     */
    public abstract void appendElementStage(B builder, RepositoryField<?> field);

}