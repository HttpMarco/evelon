package net.bytemc.evelon.repository;

import net.bytemc.evelon.repository.annotations.Ignore;
import net.bytemc.evelon.repository.annotations.PrimaryKey;
import net.bytemc.evelon.sql.ForeignKeyObject;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public record RepositoryClass<T>(Class<T> clazz) {

    /**
     * @return true if the class has a primary key.
     */
    public boolean hasPrimary() {
        return getDeclaredFields().anyMatch(it -> it.isAnnotationPresent(PrimaryKey.class));
    }

    /**
     * @return only the primary keys of the class, if there are than.
     */
    public List<Field> getPrimaries() {
        return getDeclaredFields().filter(it -> it.isAnnotationPresent(PrimaryKey.class)).toList();
    }

    /**
     * @return all fields that are not ignored. Thies' method returns all primaries and rows.
     */
    public List<Field> getRows() {
        return getDeclaredFields().filter(it -> !it.isAnnotationPresent(Ignore.class)).toList();
    }

    private Stream<Field> getDeclaredFields() {
        return Arrays.asList(clazz.getDeclaredFields()).stream();
    }

    /**
     * @param instance the instance of the class
     * @return all primary keys with their values as {@link ForeignKeyObject}
     */
    public ForeignKeyObject[] collectForeignKeyValues(@NotNull Object instance) {
        return getPrimaries().stream().map(it -> ForeignKeyObject.of(it, instance)).toArray(ForeignKeyObject[]::new);
    }
}
