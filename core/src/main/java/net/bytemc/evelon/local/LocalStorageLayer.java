package net.bytemc.evelon.local;

import net.bytemc.evelon.layers.RepositoryLayer;
import net.bytemc.evelon.query.DataQuery;
import org.jetbrains.annotations.NotNull;

public final class LocalStorageLayer implements RepositoryLayer {

    @Override
    public <T> void create(@NotNull DataQuery<T> query, T value) {
        query.getRepository().localStorage().add(LocalStorageEntry.of(value));
    }
}
