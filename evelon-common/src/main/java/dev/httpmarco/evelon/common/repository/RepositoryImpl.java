package dev.httpmarco.evelon.common.repository;

import lombok.Getter;
import lombok.experimental.Accessors;
import dev.httpmarco.evelon.common.repository.local.LocalStorageEntry;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public class RepositoryImpl<T> implements Repository<T> {

    private final List<LocalStorageEntry<T>> localData = new ArrayList<>();
    private final RepositoryClass clazz;

    public RepositoryImpl(RepositoryClass clazz) {
        this.clazz = clazz;
    }

}
