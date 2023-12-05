package net.bytemc.evelon.local;

import net.bytemc.evelon.layers.RepositoryLayer;
import net.bytemc.evelon.query.DataQuery;
import net.bytemc.evelon.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LocalStorageLayer implements RepositoryLayer {

    private static final Map<Repository<?>, List<?>> LOCAL_POOL = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> void create(DataQuery<T> query, T value) {
        var currentData = (List<T>) LOCAL_POOL.getOrDefault(query.getRepository(), new ArrayList<>());
        currentData.add(value);
        LOCAL_POOL.put(query.getRepository(), currentData);
    }
}
