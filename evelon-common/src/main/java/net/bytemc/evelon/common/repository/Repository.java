package net.bytemc.evelon.common.repository;

import net.bytemc.evelon.common.repository.local.LocalStorageEntry;

import java.util.List;

public interface Repository<T> {

    List<LocalStorageEntry<T>> localData();

    RepositoryClass clazz();

}
