package dev.httpmarco.evelon.filtering;

import dev.httpmarco.evelon.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public abstract class Filter<T, R> {

    private String id;
    private R value;

    public abstract T filter(Repository<?> repository, @Nullable R requiredType);

    public boolean requirementCheck(Class<?> clazz) {
        return true;
    }
}
