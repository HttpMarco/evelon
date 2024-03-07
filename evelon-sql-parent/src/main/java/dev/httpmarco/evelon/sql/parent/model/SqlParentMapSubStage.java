package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.model.subs.AbstractVirtualSubStage;
import dev.httpmarco.evelon.common.model.subs.MapSubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClassImpl;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import dev.httpmarco.evelon.common.repository.field.RepositoryFieldImpl;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;
import dev.httpmarco.osgan.utils.exceptions.NotImplementedException;

public final class SqlParentMapSubStage extends MapSubStage<SqlQueryBuilder> {

    @Override
    public void initializeKey(SqlQueryBuilder builder, Stage<?, ?> stage, RepositoryField parentField, Class<?> type, RepositoryObjectClass<?> parentClazz) {
        this.initialize(builder, stage, parentField, type, parentClazz, true);
    }

    @Override
    public void initializeValue(SqlQueryBuilder builder, Stage<?, ?> stage, RepositoryField parentField, Class<?> type, RepositoryObjectClass<?> parentClazz) {
        this.initialize(builder, stage, parentField, type, parentClazz, false);
    }

    private void initialize(SqlQueryBuilder Builder, Stage<?, ?> stage, RepositoryField parentField, Class<?> type, RepositoryObjectClass<?> parentClazz, boolean primary) {
        if (stage instanceof ElementStage<?, ?, ?>) {
            if (primary) {
                Builder.addRowType(new PrimaryRepositoryFieldImpl(type, parentField.id() + "_key", parentClazz));
            } else {
                Builder.addRowType(new RepositoryFieldImpl(type, parentField.id() + "_value", parentClazz));
            }
        } else if (stage instanceof SubStage<?, ?> subStage) {
            if (subStage instanceof AbstractVirtualSubStage<?>) {
                for (var field : new RepositoryObjectClassImpl<>(type).fields()) {
                    if (primary) {
                        Builder.addRowType(new PrimaryRepositoryFieldImpl(field.field(), parentClazz));
                    } else {
                        Builder.addRowType(new RepositoryFieldImpl(field.field(), parentClazz));
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
