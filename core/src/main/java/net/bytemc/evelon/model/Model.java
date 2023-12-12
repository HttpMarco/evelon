package net.bytemc.evelon.model;

import net.bytemc.evelon.repository.RepositoryField;

public interface Model {

    Stage<?, ?> findStage(RepositoryField field);

}
