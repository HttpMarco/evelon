package dev.httpmarco.evelon.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Repository class, which is used to store the data
 * @param <T> the type of the data
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter @Accessors(fluent = true)
public final class Repository<T> {

    // main name of the repository. Can be modified by the @Repository annotation
    private final String name;
    private final RepositoryClass<T> clazz;
    // todo list of current layers

    /**
     * Single point, which is used to create new builder of repo
     * @return the new builder of repo
     * @param <T> repo value type
     */
    public static <T> RepositoryBuilder<T> create(Class<T> clazz) {
        return new RepositoryBuilder<>(clazz);
    }
}