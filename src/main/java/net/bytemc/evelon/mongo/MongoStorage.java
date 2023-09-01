package net.bytemc.evelon.mongo;

import net.bytemc.evelon.Storage;
import net.bytemc.evelon.repository.RepositoryQuery;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class MongoStorage implements Storage {

    @Override
    public <T> void create(RepositoryQuery<T> query, T value) {
        //todo
    }

    @Override
    public <T> void delete(RepositoryQuery<T> query) {
        //todo
    }

    @Override
    public <T> List<T> findAll(RepositoryQuery<T> query) {
        //todo
        return null;
    }

    @Override
    public <T> @Nullable T findFirst(RepositoryQuery<T> query) {
        //todo
        return null;
    }

    @Override
    public <T> void update(RepositoryQuery<T> query, T value) {
        //todo
    }

    @Override
    public <T> boolean exists(RepositoryQuery<T> query) {
        //todo
        return false;
    }

    @Override
    public <T> long count(RepositoryQuery<T> query) {
        //todo
        return 0;
    }

    @Override
    public <T> long sum(RepositoryQuery<T> query, String id) {
        //todo
        return 0;
    }

    @Override
    public <T> double avg(RepositoryQuery<T> query, String id) {
        //todo
        return 0;
    }
}
