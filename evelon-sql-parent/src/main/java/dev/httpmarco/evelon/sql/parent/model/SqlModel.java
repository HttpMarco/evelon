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
public final class SqlModel implements Model {

    private final List<Stage> stages = new ArrayList<>();

    @Override
    public Stage findStage(RepositoryField field) {
        return null;
    }
}
