package net.bytemc.evelon.local;

import net.bytemc.evelon.layers.RepositoryLayer;
import net.bytemc.evelon.query.DataQuery;
import net.bytemc.evelon.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LocalStorageLayer implements RepositoryLayer {

    @Override
    public <T> void create(DataQuery<T> query, T value) {
        query.getRepository().localStorage().add(LocalStorageEntry.of(value));
    }
}
