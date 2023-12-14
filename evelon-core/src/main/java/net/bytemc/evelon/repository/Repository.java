package net.bytemc.evelon.repository;

import net.bytemc.evelon.layers.RepositoryLayer;
import net.bytemc.evelon.local.LocalStorageEntry;

import java.util.ArrayList;
import java.util.List;

public final class Repository<T> {

    private final List<LocalStorageEntry<T>> localData = new ArrayList<>();

    private final RepositoryLayer[] layers;
    private final RepositoryClass<T> clazz;

    public Repository(RepositoryClass<T> clazz) {
        this.clazz = clazz;

        //TODO
        this.layers = new RepositoryLayer[0];
    }

    public List<LocalStorageEntry<T>> localStorage() {
        return this.localData;
    }

    public RepositoryClass<T> clazz() {
        return this.clazz;
    }

    public RepositoryLayer[] layers() {
        return this.layers;
    }
}
