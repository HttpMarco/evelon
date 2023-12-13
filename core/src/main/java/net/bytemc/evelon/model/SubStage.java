package net.bytemc.evelon.model;

import net.bytemc.evelon.repository.RepositoryClass;

public interface SubStage<T, I> extends Stage {

    T serialize(String id, I input, RepositoryClass<I> repositoryClass);

    @SuppressWarnings("unchecked")
    default T serializeCommon(String id, Object input, RepositoryClass<?> repositoryClass) {
        return serialize(id, (I) input, (RepositoryClass<I>) repositoryClass);
    }
}
