package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.stage.types.ObjectType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;
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

    /**
     * Scan all fields of the class and build the repository class
     * @param clazz the class to scan
     * @return the repository class
     * @param <C> the type of the class
     */
    private <C> RepositoryClass<C> scanClass(Class<C> clazz) {
        var type = Evelon.instance().stageService().typeOf(clazz);
        if (type instanceof ObjectType) {
            return new RepositoryObjectClass<C>(clazz, type, scanObjectFields(clazz));
        }
        return new RepositoryClass<>(clazz, type);
    }

    /**
     * Collect all fields of the object class
     * @param clazz the object with fields
     * @return set of fields
     */
    private Set<RepositoryObjectClass.ObjectField<?>> scanObjectFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).map(it -> new RepositoryObjectClass.ObjectField<>(it.getType(), scanClass(it.getType()).type(), it)).collect(Collectors.toSet());
    }

    /**
     * Build the repository
     * @return the repository
     */
    public Repository<T> build() {
        // todo: add layers and init them
        return new Repository<>(null, scanClass(clazz));
    }
}