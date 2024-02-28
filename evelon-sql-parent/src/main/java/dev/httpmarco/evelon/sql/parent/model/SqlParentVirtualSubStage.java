package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.subs.AbstractVirtualSubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.sql.parent.SqlQueryBuilder;

public final class SqlParentVirtualSubStage extends AbstractVirtualSubStage<SqlQueryBuilder> {

    @Override
    public void initializeSubElement(SqlQueryBuilder query, RepositoryField field) {
        query.withField(field);
    }
}
