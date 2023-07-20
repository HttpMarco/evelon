package net.bytemc.evelon.sql;

import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.process.*;
import net.bytemc.evelon.storages.Storage;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class DatabaseStorage implements Storage {

    @Override
    public <T> void create(RepositoryQuery<T> query, T value) {
        ColumnEntryCreationProcess.create(query, value);
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
    public <T> int count(RepositoryQuery<T> query) {
        //todo
        return -1;
    }
}
