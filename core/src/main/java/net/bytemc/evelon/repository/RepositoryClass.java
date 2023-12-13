package net.bytemc.evelon.repository;

import net.bytemc.evelon.annotations.PrimaryKey;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class RepositoryClass<T> {

    private final Class<T> clazz;
    private final Repository<?> repository;

    private final RepositoryField[] fields;
    private final Set<RepositoryField> primaries = new HashSet<>();

    public RepositoryClass(@NotNull Class<T> clazz, Repository<?> repository) {
        this.clazz = clazz;
        this.repository = repository;
        this.fields = Arrays.stream(clazz.getDeclaredFields())
                .map(field -> {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        var primaryField = new PrimaryRepositoryField(field, repository);
                        primaries.add(primaryField);
                        return primaryField;
                    }
                    return new RepositoryField(field, repository);
                }).toArray(value -> new RepositoryField[0]);
    }

    public RepositoryClass<?> subClass(RepositoryField field) {
        return new RepositoryClass<>(field.type(), repository);
    }

    public Class<?> getOrigin() {
        return this.clazz;
    }

    public RepositoryField[] getFields() {
        return fields;
    }

    public Set<RepositoryField> getPrimaries() {
        return this.primaries;
    }

    public boolean hasField(String id) {
        return Arrays.stream(fields).anyMatch(field -> field.getName().equals(id));
    }

    public RepositoryField getField(String id) {
        return Arrays.stream(fields).filter(field -> field.getName().equals(id)).findFirst().orElseThrow();
    }
}