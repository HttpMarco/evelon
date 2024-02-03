package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.local.LocalCacheRepositoryImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RepositoryBuilder<T> {

    private final Class<T> clazz;

    // current repository settings
    private boolean useLocalStorage = false;

    public static <R> RepositoryBuilder<R> of(Class<R> clazz) {
        return new RepositoryBuilder<>(clazz);
    }

    public RepositoryBuilder<T> withLocalStorage() {
        this.useLocalStorage = true;
        return this;
    }

    public Repository<T> build() {
        var repository = useLocalStorage ? new LocalCacheRepositoryImpl<>(clazz) : new RepositoryImpl<>(clazz);

        return repository;
    }
}
