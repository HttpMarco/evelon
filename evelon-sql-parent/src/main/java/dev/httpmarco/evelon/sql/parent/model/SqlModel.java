package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public final class SqlModel extends Model {

    @Override
    public void applyPlatformStages() {
        stages().add(new SqlParentVirtualSubStage());
        stages().add(new SqlParentParameterStage());
    }
}
