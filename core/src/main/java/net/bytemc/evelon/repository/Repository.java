package net.bytemc.evelon.repository;

public final class Repository<T> {

    private final RepositoryClass<T> clazz;

    public Repository(RepositoryClass<T> clazz) {
        this.clazz = clazz;
    }
}
