package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.repository.RepositoryField;

public interface Model {

    Stage findStage(RepositoryField field);

}
