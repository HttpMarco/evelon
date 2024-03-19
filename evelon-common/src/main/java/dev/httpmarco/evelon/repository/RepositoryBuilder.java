package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.annotation.Entity;
import dev.httpmarco.evelon.annotation.Row;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.stage.Type;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Builder of the repository
 * Scan repository class and load all metadata of stages
 *
 * @param <T> the type of the repository
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public final class RepositoryBuilder<T> {

    // class of the repository
    private final Class<T> clazz;

    // all collect layers in the right order
    private final Set<Layer> layers = new LinkedHashSet<>();

    /**
     * Scan all fields of the class and build the repository class
     *
     * @param clazz      the class to scan
     * @param repository the repository
     * @param <C>        the type of the class
     * @return the repository class
     */
    private <C> @NotNull RepositoryClass<C> scanClass(Repository<T> repository, Class<C> clazz) {
        var type = Type.typeOf(clazz);

        if (type == Type.OBJECT) {
            return new RepositoryObjectClass<>(repository, clazz, type, scanObjectFields(repository, clazz));
        }

        return new RepositoryClass<>(repository, clazz, type);
    }

    /**
     * Collect all fields of the object class
     *
     * @param clazz      the object with fields
     * @param repository the repository
     * @return set of fields
     */
    private Set<RepositoryObjectClass.ObjectField<?>> scanObjectFields(Repository<T> repository, @NotNull Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(it -> !it.isAnnotationPresent(Row.class) || !it.getDeclaredAnnotation(Row.class).ignore())
                .map(it -> new RepositoryObjectClass.ObjectField<>(repository, it.getType(), scanClass(repository, it.getType()).type(), it))
                .collect(Collectors.toSet());
    }

    /**
     * Add a layer to the repository query
     *
     * @param layer the layer class to add
     * @return the builder
     */
    public RepositoryBuilder<T> layer(Class<? extends Layer> layer) {
        this.layers.add(Evelon.instance().layerService().find(layer));
        return this;
    }

    /**
     * Get the name of the repository and prove the name conventions
     *
     * @return the name
     */
    private @NotNull String name() {
        if (clazz.isAnnotationPresent(Entity.class)) {
            var name = clazz.getAnnotation(Entity.class).name();
            if (!name.isBlank()) {
                return name;
            }
        }
        return clazz.getSimpleName();
    }

    /**
     * Build the repository
     *
     * @return the repository
     */
    @Contract(" -> new")
    public @NotNull Repository<T> build() {
        var repository = new Repository<T>(name(), layers);
        repository.clazz(scanClass(repository, clazz));
        return repository;
    }
}