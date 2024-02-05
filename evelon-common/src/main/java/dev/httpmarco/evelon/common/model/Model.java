package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.repository.RepositoryField;

import java.util.List;

public interface Model {

    List<Stage> stages();

    Stage findStage(RepositoryField field);

}
