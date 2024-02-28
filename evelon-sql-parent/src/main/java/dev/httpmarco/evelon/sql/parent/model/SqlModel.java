package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.sql.parent.SqlQueryBuilder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class SqlModel extends Model<SqlQueryBuilder> {

    @Override
    public void applyPlatformStages() {
        stages().add(new SqlParentParameterStage());
        stages().add(new SqlParentVirtualSubStage());
    }
}
