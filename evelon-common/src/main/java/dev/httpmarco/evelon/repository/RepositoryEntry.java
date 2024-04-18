package dev.httpmarco.evelon.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
@ToString
public class RepositoryEntry {

    private final String id;
    // the original class of the entry
    private final Class<?> clazz;
    // current type of field
    private final RepositoryEntryType type;
    // the constants of the entry
    private final RepositoryConstantPool constants = new RepositoryConstantPool();

}