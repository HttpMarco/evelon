package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.subs.AbstractCollectionSubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;
import org.jetbrains.annotations.NotNull;

public final class SqlParentAbstractCollectionSubStage extends AbstractCollectionSubStage<SqlQueryBuilder> {

    @Override
    public void appendElementStage(@NotNull SqlQueryBuilder builder, RepositoryField<?> field) {
        builder.addRowType(field);
    }
}
