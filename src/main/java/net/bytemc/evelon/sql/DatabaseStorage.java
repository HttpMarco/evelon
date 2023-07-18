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
        ColumnEntryDeletionProcess.delete(query);
    }

    @Override
    public <T> List<T> findAll(RepositoryQuery<T> query) {
        return ColumnEntryFindProcess.find(query, -1);
    }

    @Override
    public <T> @Nullable T findFirst(RepositoryQuery<T> query) {
        var list = ColumnEntryFindProcess.find(query, 1);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    @Override
    public <T> void update(RepositoryQuery<T> query, T value) {
        ColumnEntryUpdateProcess.update(query, value);
    }

    @Override
    public <T> boolean exists(RepositoryQuery<T> query) {
        return ColumExistsProcess.exists(query);
    }

    @Override
    public <T> int count(RepositoryQuery<T> query) {
        return TableEntryCountProcess.countEntries(query);
    }
}
