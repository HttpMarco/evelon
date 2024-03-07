package dev.httpmarco.evelon.common.repository.clazz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class RepositoryClassImpl<T> implements RepositoryClass<T> {

    private final Class<T> clazz;

}
