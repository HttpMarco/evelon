package dev.httpmarco.evelon.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Repository class, which is used to store the data
 *
 * @param <T> the type of the data
 */
@Getter
@Accessors(fluent = true)
@Setter(AccessLevel.PACKAGE)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class Repository<T> {

    // main name of the repository. Can be modified by the @Repository annotation
    private final String name;
    private RepositoryClass<T> clazz;
    // todo list of current layers

    /**
     * Single point, which is used to create new builder of repo
     *
     * @param <T> repo value type
     * @return the new builder of repo
     */
    @Contract("_ -> new")
    public static <T> @NotNull RepositoryBuilder<T> create(Class<T> clazz) {
        return new RepositoryBuilder<>(clazz);
    }
}