package dev.httpmarco.evelon.common.repository.clazz;

import org.jetbrains.annotations.Nullable;

public interface RepositoryClass<T> {

    Class<T> clazz();

    String name();

    default RepositoryObjectClass<T> asObjectClass() {
        if (this instanceof RepositoryObjectClass<T> objectClass) {
            return objectClass;
        }
        throw new RuntimeException("RepositoryClass is not an object class: " + this.getClass().getSimpleName());
    }
}
