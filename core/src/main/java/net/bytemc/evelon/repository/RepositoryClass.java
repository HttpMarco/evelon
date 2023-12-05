package net.bytemc.evelon.repository;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class RepositoryClass<T> {

    private final Class<T> clazz;
    private final RepositoryField[] fields;

    public RepositoryClass(@NotNull Class<T> clazz) {
        this.clazz = clazz;
        this.fields = Arrays.stream(clazz.getDeclaredFields())
                .map(RepositoryField::new).toArray(value -> new RepositoryField[0]);
    }

    public Class<?> getOrigin() {
        return this.clazz;
    }

    public RepositoryField[] getFields() {
        return fields;
    }
}
