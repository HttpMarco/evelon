package net.bytemc.evelon.api.repository;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class RepositoryImpl implements Repository {

    private final RepositoryClass clazz;

    public RepositoryImpl(RepositoryClass clazz) {
        this.clazz = clazz;
    }

}
