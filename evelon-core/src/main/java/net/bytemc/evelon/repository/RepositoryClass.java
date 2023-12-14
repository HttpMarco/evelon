package net.bytemc.evelon.repository;

import lombok.Getter;
import net.bytemc.evelon.annotations.PrimaryKey;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
public final class RepositoryClass<T> {

    private final Class<T> origin;
    private final Repository<?> repository;
    private final RepositoryField[] fields;
    private final Set<RepositoryField> primaries = new HashSet<>();

    public RepositoryClass(@NotNull Class<T> clazz, Repository<?> repository) {
        this.origin = clazz;
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

    public boolean hasField(String id) {
        return Arrays.stream(fields).anyMatch(field -> field.getName().equals(id));
    }

    public RepositoryField getField(String id) {
        return Arrays.stream(fields).filter(field -> field.getName().equals(id)).findFirst().orElseThrow();
    }
}