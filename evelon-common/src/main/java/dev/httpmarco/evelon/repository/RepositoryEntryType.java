package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.repository.entries.RepositoryCollectionEntry;
import dev.httpmarco.evelon.repository.entries.RepositoryMapEntry;
import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import dev.httpmarco.evelon.repository.exception.UnsupportedEntryTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum RepositoryEntryType {

    PARAMETER(Class::isPrimitive, (id, clazz, field) -> new RepositoryEntry(id, clazz)),

    ENUM(Class::isEnum, (id, clazz, field) -> new RepositoryEntry(id, clazz)),

    UNIQUE_ID(it -> it.equals(java.util.UUID.class), (id, clazz, field) -> new RepositoryEntry(id, clazz)),

    MAP(Map.class::isAssignableFrom, (id, clazz, field) -> new RepositoryMapEntry(id, field)),

    COLLECTION(Collection.class::isAssignableFrom, (id, clazz, field) -> new RepositoryCollectionEntry(id, field)),

    OBJECT(it -> !it.isSynthetic(), (id, clazz, field) -> new RepositoryObjectEntry(id, clazz)),

    UNDEFINED(it -> true, (id, it, field) -> {
        throw new UnsupportedEntryTypeException(it);
    });

    // we reduce enum values loading to a set of values
    public static final List<RepositoryEntryType> ENTRY_TYPES = Arrays.stream(values()).toList();

    private final EntryAccessPredicate accessPredicate;
    private final EntryGeneration generation;

    public static RepositoryEntryType scan(Class<?> clazz) {
        return ENTRY_TYPES.stream()
                .filter(it -> it.accessPredicate.test(clazz))
                .findFirst()
                .orElse(UNDEFINED);
    }
}
