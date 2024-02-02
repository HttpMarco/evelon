package dev.httpmarco.evelon.common.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RepositoryBuilder<T> {

    private final Class<T> clazz;

    public static <R> RepositoryBuilder<R> of(Class<R> clazz) {
        return new RepositoryBuilder<>(clazz);
    }

    public Repository<T> build() {
        return new RepositoryImpl<>(clazz);
    }
}
