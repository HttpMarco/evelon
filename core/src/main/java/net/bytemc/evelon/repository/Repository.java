package net.bytemc.evelon.repository;

final class Repository<T> {

    private final RepositoryClass<T> clazz;

    public Repository(RepositoryClass<T> clazz) {
        this.clazz = clazz;
    }
}
