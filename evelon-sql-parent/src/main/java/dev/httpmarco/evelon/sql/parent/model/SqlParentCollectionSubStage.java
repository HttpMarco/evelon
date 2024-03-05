package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.subs.CollectionSubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;
import org.jetbrains.annotations.NotNull;

public final class SqlParentCollectionSubStage extends CollectionSubStage<SqlQueryBuilder> {

    @Override
    public void appendElementStage(@NotNull SqlQueryBuilder builder, RepositoryField field) {
        builder.addRowType(field);
    }
}
