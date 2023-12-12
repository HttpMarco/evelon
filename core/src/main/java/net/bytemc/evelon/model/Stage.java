package net.bytemc.evelon.model;

import net.bytemc.evelon.repository.RepositoryClass;

public interface Stage<T, I> {

    T serialize(String id, I input, RepositoryClass<I> repositoryClass);

    boolean isElement(Class<?> type);

}