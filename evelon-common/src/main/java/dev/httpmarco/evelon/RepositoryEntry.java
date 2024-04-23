package dev.httpmarco.evelon;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @Getter(AccessLevel.PRIVATE)
    private final Map<RepositoryConstant<?>, Object> constants = new ConcurrentHashMap<>();


    public void constantOption(RepositoryConstant<Void> constant) {
        constants.put(constant, true);
    }

    public <T> T constant(RepositoryConstant<T> constant, T value) {
        constants.put(constant, value);
        return value;
    }

    public boolean hasConstant(RepositoryConstant<?> constant) {
        return constants.containsKey(constant);
    }

    @SuppressWarnings("unchecked")
    public <T> T constant(RepositoryConstant<T> constant) {
        return (T) constants.get(constant);
    }
}