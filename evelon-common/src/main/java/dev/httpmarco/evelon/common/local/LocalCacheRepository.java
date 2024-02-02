package dev.httpmarco.evelon.common.local;

import dev.httpmarco.evelon.common.repository.Repository;

import java.util.List;

public interface LocalCacheRepository<T> extends Repository<T> {

    List<LocalStorageEntry<T>> localData();

}
