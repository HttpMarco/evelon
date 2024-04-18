package dev.httpmarco.evelon.repository;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
@ToString
public class RepositoryEntry {

    private final String id;
    // the original class of the entry
    private final Class<?> clazz;
    // parent of the parameter
    private final RepositoryExternalEntry parent;
    // the constants of the entry
    private final RepositoryConstantPool constants = new RepositoryConstantPool();

}