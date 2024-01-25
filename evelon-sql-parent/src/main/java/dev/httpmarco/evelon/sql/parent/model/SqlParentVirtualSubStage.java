package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.model.subs.AbstractVirtualSubStage;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.sql.parent.sql.SqlQueryBuilder;
import org.jetbrains.annotations.NotNull;

public final class SqlParentVirtualSubStage extends AbstractVirtualSubStage<SqlQueryBuilder> {

    @Override
    public void initialize(String stageId, @NotNull RepositoryObjectClass<?> clazz, SqlQueryBuilder query) {
        for (var field : clazz.fields()) {

            if (field.stage() instanceof SubStage<?> subStage) {
                subStage.initializeWithMapping(stageId + "_" + field.id(), null, query.subQuery());
            } else {
                query.withField(field);
            }
        }
    }
}
