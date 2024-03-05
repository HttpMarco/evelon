package dev.httpmarco.evelon.common.repository.clazz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class RepositoryClassImpl<C> implements RepositoryClass<C> {

    private final Class<C> clazz;

    @Setter
    private String name;

    public String name() {
        return name == null ? clazz.getSimpleName() : name;
    }
}
