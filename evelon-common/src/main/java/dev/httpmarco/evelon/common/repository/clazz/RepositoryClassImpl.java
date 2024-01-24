package dev.httpmarco.evelon.common.repository.clazz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class RepositoryClassImpl<C> implements RepositoryClass<C> {

    private final Class<C> clazz;

}
