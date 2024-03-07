package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClassImpl;

public abstract class AbstractVirtualSubStage<R extends Builder<?, ?>> implements SubStage<R> {

    @Override
    public void initialize(String stageId, Model<?> model, RepositoryField ownField, RepositoryObjectClass<?> clazz, R queries) {
        for (var field : clazz.fields()) {
            if (field.stage(model) instanceof SubStage<?> subStage) {
                subStage.initializeWithMapping(stageId + "_" + field.id(), model, field, new RepositoryObjectClassImpl<>(field.fieldType()), queries.subBuilder(field.id()));
            } else {
                initializeSubElement(queries, field);
            }
        }
    }

    @Override
    public void create(String stageId, Model<?> model, RepositoryField ownField, RepositoryObjectClass<?> clazz, R queries) {
        // todo
    }

    public abstract void initializeSubElement(R query, RepositoryField field);

    @Override
    public boolean isElement(Class<?> type) {
        return true;
    }
}
