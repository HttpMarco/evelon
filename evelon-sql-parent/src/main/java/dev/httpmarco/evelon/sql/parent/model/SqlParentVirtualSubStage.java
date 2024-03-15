package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.subs.AbstractVirtualSubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import org.jetbrains.annotations.NotNull;

public final class SqlParentVirtualSubStage extends AbstractVirtualSubStage {

    /*
    @Override
    public void appendParameter(@NotNull SqlQueryBuilder query, RepositoryField<?> field) {
        query.addRowType(field);
    }

    @Override
    public void transformData(SqlQueryBuilder builder, RepositoryField<?>... requiredFields) {
        builder.query(i -> {
            while (i.next()) {
                for (var field : requiredFields) {
                    builder.appendValue(field.id(), i.getObject(field.id()));
                }
            }
            return null;
        }, // todo: find a better way (return)
        null);
    }

     */
}
