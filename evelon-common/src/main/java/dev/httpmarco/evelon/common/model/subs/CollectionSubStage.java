package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.common.repository.field.RepositoryFieldImpl;
import dev.httpmarco.osgan.reflections.Reflections;

import java.util.Collection;

public abstract class CollectionSubStage<R extends Builder<R, ?>> implements SubStage<R> {

    @Override
    public void initialize(String stageId, Model<?> model, RepositoryField ownField, RepositoryObjectClass<?> clazz, R queries) {
        var collectionListType = Reflections.of(ownField.field()).generics();

        if (collectionListType.length != 1) {
            throw new IllegalStateException("Collection stage tpe has multiple elements. That is not a requirement of collections.");
        }

        var collectionType = collectionListType[0];

        // add foreign kek linking
        // todo
       // queries.foreignLinkings(ownField.parentClass().asObjectClass().primaryFields());

        var stage = model.findStage(collectionType);
        if (stage instanceof ElementStage<?, ?, ?>) {
            this.appendElementStage(queries, new RepositoryFieldImpl(collectionType, ownField.id(), clazz));
        } else if (stage instanceof SubStage<?> substage) {
            // todo: implementation
        } else throw new RuntimeException("This stage is not supported yet.");
    }

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    /**
     * Append the element stage to the builder. Here we cant know the type of the element, so we need to pass the class
     *
     * @param builder the builder
     * @param field the class of the element
     */
    public abstract void appendElementStage(R builder, RepositoryField field);

}