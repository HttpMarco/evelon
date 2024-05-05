package dev.httpmarco.evelon.query.common;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.QueryFiltering;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public final class RepositoryFiltering<T> extends RepositoryQuery<T> implements QueryFiltering<T> {

    private final Map<Layer<?>, List<dev.httpmarco.evelon.filtering.Filter<?, ?>>> filters = new HashMap<>();

    public RepositoryFiltering(Repository<T> repository, @NotNull Set<Layer<?>> layers) {
        super(repository);

        for (var layer : layers) {
            this.filters.put(layer, new ArrayList<>());
        }
    }

    @Override
    public QueryFiltering<T> match(String id, Object object) {
        for (var layer : this.filters.keySet()) {
            filters.get(layer).add(layer.filterHandler().match(id, object));
        }
        return this;
    }
}
