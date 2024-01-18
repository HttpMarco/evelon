package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryClassImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import dev.httpmarco.evelon.common.repository.local.LocalStorageEntry;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public final class RepositoryImpl<T> implements Repository<T> {

    private final List<LocalStorageEntry<T>> localData = new ArrayList<>();
    private final RepositoryClass<?> clazz;

    public RepositoryImpl(Class<T> clazz) {
        this.clazz = new RepositoryClassImpl(clazz);
    }

}
