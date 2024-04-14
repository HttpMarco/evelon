package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.repository.external.RepositoryCollectionEntry;
import dev.httpmarco.evelon.repository.external.RepositoryMapEntry;
import dev.httpmarco.evelon.repository.external.RepositoryObjectEntry;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.*;

@AllArgsConstructor
public enum RepositoryEntryType {

    PARAMETER(it -> it.isEnum() || it.isPrimitive() || it.equals(UUID.class), (id, clazz, type, field) -> new RepositoryEntry(id, clazz, type)),
    MAP(Map.class::isAssignableFrom, (id, clazz, type, field) -> new RepositoryMapEntry(id, field)),
    COLLECTION(Collection.class::isAssignableFrom, (id, clazz, type, field) -> new RepositoryCollectionEntry(id, field)),
    OBJECT(it -> !it.isSynthetic(), (id, clazz, field, type) -> new RepositoryObjectEntry(id, clazz));

    // we reduce enum values loading to a set of values
    private static final List<RepositoryEntryType> ENTRY_TYPES = Arrays.stream(values()).toList();

    private final EntryAccess accessPredicate;
    private final EntryGeneration generation;

    private static RepositoryEntry find(String id, Field field, Class<?> clazz) {
        var type = ENTRY_TYPES.stream().filter(it -> it.accessPredicate.test(clazz)).findFirst().orElseThrow();
        return type.generation.generate(id, clazz, type, field);
    }

    public static RepositoryEntry find(String id, Class<?> clazz) {
        return find(id, null, clazz);
    }

    public static RepositoryEntry find(String id, Field field) {
        return find(id, field, field.getType());
    }
}