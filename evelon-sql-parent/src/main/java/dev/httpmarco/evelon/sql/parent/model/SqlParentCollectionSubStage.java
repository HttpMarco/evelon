package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.subs.CollectionSubStage;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;

public class SqlParentCollectionSubStage extends CollectionSubStage<SqlQueryBuilder> {

    @Override
    public void appendElementStage(SqlQueryBuilder builder, RepositoryClass<?> clazz) {
        builder.addRowType(clazz);
    }
}
