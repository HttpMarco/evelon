package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.repository.RepositoryField;

public class EmptyModel implements Model{

    @Override
    public Stage findStage(RepositoryField field) {
        // not needed
        return null;
    }
}
