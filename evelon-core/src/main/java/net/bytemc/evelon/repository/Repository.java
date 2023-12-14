package net.bytemc.evelon.repository;

import lombok.Getter;
import net.bytemc.evelon.layers.RepositoryLayer;
import net.bytemc.evelon.local.LocalStorageEntry;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Repository<T> {

    private final List<LocalStorageEntry<T>> localData = new ArrayList<>();

    private final RepositoryLayer[] layers;
    private final RepositoryClass<T> clazz;

    public Repository(RepositoryClass<T> clazz) {
        this.clazz = clazz;

        //TODO
        this.layers = new RepositoryLayer[0];
    }
}
