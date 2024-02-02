package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClassImpl;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class RepositoryImpl<T> implements Repository<T> {

    private final RepositoryClass<T> clazz;

    public RepositoryImpl(Class<T> clazz) {
        this.clazz = new RepositoryClassImpl<>(clazz);
    }

}
