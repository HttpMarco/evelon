package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.repository.local.LocalStorageEntry;

import java.util.List;

public interface Repository<T> {

    List<LocalStorageEntry<T>> localData();

    RepositoryClass clazz();

}
