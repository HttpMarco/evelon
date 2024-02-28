package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.common.repository.field.RepositoryFieldImpl;
import dev.httpmarco.evelon.common.utils.GenericReader;

import java.util.Collection;

public abstract class CollectionSubStage<R extends Builder> implements SubStage<R> {

    @Override
    public void initialize(String stageId, Model<?> model, RepositoryField ownField, RepositoryObjectClass<?> clazz, R queries) {
        var collectionType = GenericReader.readGenericFromClass(ownField.field());

        if (collectionType.length > 1) {
            throw new IllegalStateException("Collection stage tpe has multiple elements. That is not a requirement of collections.");
        }

        var stage = model.findStage(collectionType[0]);
        if (stage instanceof ElementStage<?, ?, ?>) {
            queries.withField(new RepositoryFieldImpl(collectionType[0], ownField.id(), clazz));
        } else if (stage instanceof SubStage<?> substage) {
            // todo
        } else {
            throw new UnsupportedOperationException("This stage is not supported yet.");
        }
    }

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }


}
