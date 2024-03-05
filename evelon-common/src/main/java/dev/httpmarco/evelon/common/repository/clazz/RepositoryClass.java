package dev.httpmarco.evelon.common.repository.clazz;

public interface RepositoryClass<T> {

    Class<T> clazz();

    default RepositoryObjectClass<T> asObjectClass() {
        if (this instanceof RepositoryObjectClass<T> objectClass) {
            return objectClass;
        }
        throw new RuntimeException("RepositoryClass is not an object class: " + this.getClass().getSimpleName());
    }
}
