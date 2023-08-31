package net.bytemc.evelon.repository.properties;

import net.bytemc.evelon.repository.Filter;
import net.bytemc.evelon.repository.Repository;

public interface StartupProperties<T> extends Properties {
    static <T> StartupProperties<T> empty() {
        return repository -> {
        };
    }

    static <T> StartupProperties<T> syncAll() {
        return new SyncAllStartupProperty<>();
    }

    static <T> StartupProperties<T> syncAllWithFilters(Filter... filters) {
        return new SyncAllWithFiltersStartupProperty<>(filters);
    }

    void initialize(Repository<T> repository);
}
