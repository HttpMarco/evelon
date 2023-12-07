package net.bytemc.evelon.query;

import net.bytemc.evelon.repository.Repository;

public final class DataQuery<T> {

    private Repository<T> repository;

    public Repository<T> getRepository() {
        return repository;
    }
}
