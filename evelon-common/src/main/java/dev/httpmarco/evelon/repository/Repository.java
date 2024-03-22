package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.Query;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

/**
 * Repository class, which is used to store the data
 *
 * @param <T> the type of the data
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class Repository<T> {

    // main name of the repository. Can be modified by the @Repository annotation
    private final String name;

    // clazz need the repository to be created, also we need to wait for the class
    @Setter(AccessLevel.PACKAGE)
    private RepositoryClass<T> clazz;

    // repository ordered layers
    private final Set<Layer> layers;

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

    /**
     * Create new query action
     * @return new query
     */
    public Query<T> query() {
        return new Query<>(this);
    }
}