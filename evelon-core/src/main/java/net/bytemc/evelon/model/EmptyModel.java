package net.bytemc.evelon.model;

import net.bytemc.evelon.repository.RepositoryField;

public class EmptyModel implements Model{

    @Override
    public Stage findStage(RepositoryField field) {
        // not needed
        return null;
    }
}
