package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.subs.AbstractVirtualSubStage;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;

public final class SqlParentVirtualSubStage extends AbstractVirtualSubStage<SqlQueryBuilder> {

    @Override
    public void initializeSubElement(SqlQueryBuilder query, RepositoryClass<?> field) {
        query.addRowType(field);
    }
}
