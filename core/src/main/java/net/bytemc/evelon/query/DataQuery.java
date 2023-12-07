package net.bytemc.evelon.query;

import net.bytemc.evelon.filters.Filter;
import net.bytemc.evelon.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public final class DataQuery<T> {

    private final Repository<T> repository;
    private final List<Filter<?, ?>> filters = new ArrayList<>();

    public DataQuery(Repository<T> repository) {
        this.repository = repository;
    }

    public Repository<T> getRepository() {
        return repository;
    }
}
