package dev.httpmarco.evelon.common.local;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.repository.RepositoryField;

public class LocalModel implements Model {

    @Override
    public Stage findStage(RepositoryField field) {
        // not needed
        return null;
    }
}
