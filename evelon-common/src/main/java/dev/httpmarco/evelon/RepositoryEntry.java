package dev.httpmarco.evelon;

import dev.httpmarco.evelon.constant.ConstantPool;
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
    private final ConstantPool constants = new ConstantPool();

    public boolean isExternal() {
        return this instanceof RepositoryExternalEntry;
    }
}