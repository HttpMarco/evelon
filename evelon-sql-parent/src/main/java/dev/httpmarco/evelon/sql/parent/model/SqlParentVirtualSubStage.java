package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.model.subs.AbstractVirtualSubStage;
import dev.httpmarco.evelon.common.repository.RepositoryClass;
import dev.httpmarco.evelon.sql.parent.sql.SqlQueryBuilder;

public final class SqlParentVirtualSubStage extends AbstractVirtualSubStage<SqlQueryBuilder> {

    @Override
    public void initialize(String stageId, RepositoryClass<?> clazz, SqlQueryBuilder query) {
        for (var field : clazz.fields()) {
            //check staging
            query.withField(field);

            //TODO
            SubStage possibleFieldStage = null;
            // TODO
            possibleFieldStage.initialize(null, null, query.subQuery());
        }
    }
}
