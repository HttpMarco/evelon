package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.model.subs.AbstractVirtualSubStage;
import dev.httpmarco.evelon.common.model.subs.AbstractMapSubStage;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClassImpl;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import dev.httpmarco.evelon.common.repository.field.RepositoryFieldImpl;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;
import dev.httpmarco.osgan.utils.exceptions.NotImplementedException;

public final class SqlParentAbstractMapSubStage extends AbstractMapSubStage<SqlQueryBuilder> {

    @Override
    public void initializeMapElement(Repository<?> repository, boolean key, SqlQueryBuilder builder, Stage<?, SqlQueryBuilder> stage, RepositoryField<?> parentField, RepositoryClass<?> clazz, RepositoryObjectClass<?> parentClass) {
        if (stage.isElementStage()) {
            if (key) {
                builder.addRowType(new PrimaryRepositoryFieldImpl(repository, clazz, parentField.id() + "_key", parentClass));
            } else {
                builder.addRowType(new RepositoryFieldImpl(repository, clazz, parentField.id() + "_value", parentClass));
            }
        } else if (stage instanceof SubStage<?, ?> subStage) {
            if (subStage instanceof AbstractVirtualSubStage<?>) {
                for (var field : new RepositoryObjectClassImpl<>(repository, clazz.clazz()).fields()) {
                    if (key) {
                        builder.addRowType(new PrimaryRepositoryFieldImpl(repository, field.field(), parentClass));
                    } else {
                        builder.addRowType(new RepositoryFieldImpl(repository, field.field(), parentClass));
                    }
                }
            } else {
                throw new NotImplementedException("Unknown sub stage type: " + subStage.getClass().getName());
            }
        } else {
            throw new IllegalStateException("Unknown stage type: " + stage);
        }
    }
}
