package dev.httpmarco.evelon.common.repository.clazz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class RepositoryClassImpl<C> implements RepositoryClass<C> {

    private final Class<C> clazz;

}
