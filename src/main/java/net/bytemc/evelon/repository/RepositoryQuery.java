package net.bytemc.evelon.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytemc.evelon.local.LocalStorage;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.sql.DatabaseHelper;
import net.bytemc.evelon.sql.DatabaseStorage;
import net.bytemc.evelon.storages.StorageHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public final class RepositoryQuery<T> {

    private final List<Filter> filters = new ArrayList<>();
    private final Repository<T> repository;

    public RepositoryQueryActions<T> local() {
        return new RepositoryQueryActions<>(this, RepositoryDepartureOrder.LOCAL);
    }

    public RepositoryQueryActions<T> database() {
        return new RepositoryQueryActions<>(this, RepositoryDepartureOrder.DATABASE);
    }

    public RepositoryQueryActions<T> chronological() {
        return new RepositoryQueryActions<>(this, RepositoryDepartureOrder.CHRONOLOGICAL);
    }

    public RepositoryQuery<T> filter(Filter filter) {
        this.filters.add(filter);
        return this;
    }

    public void delete() {
        StorageHandler.getStorage(LocalStorage.class).delete(this);
        StorageHandler.getStorage(DatabaseStorage.class).delete(this);
    }

    public void create(T value) {
        StorageHandler.getStorage(LocalStorage.class).create(this, value);
        StorageHandler.getStorage(DatabaseStorage.class).create(this, value);
    }

    public void createIfNotExists(T value) {
        var localStorage = StorageHandler.getStorage(LocalStorage.class);

        for (var primary : getRepository().repositoryClass().getPrimaries()) {
            filter(Filters.match(DatabaseHelper.getRowName(primary), Reflections.readField(value, primary)));
        }

        if(!localStorage.exists(this)) {
            localStorage.create(this, value);
        }
        var databaseStorage = StorageHandler.getStorage(DatabaseStorage.class);
        if(!databaseStorage.exists(this)) {
            databaseStorage.create(this, value);
        }
    }
}