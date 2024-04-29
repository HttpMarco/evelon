package dev.httpmarco.evelon.query.common;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.Filter;
import dev.httpmarco.evelon.query.QueryFilter;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public final class RepositoryFilter<T> extends RepositoryQuery<T> implements QueryFilter<T> {

    private final Map<Layer<?>, List<dev.httpmarco.evelon.filtering.Filter<?, ?>>> filters = new HashMap<>();

    public RepositoryFilter(Repository<T> repository, @NotNull Set<Layer<?>> layers) {
        super(repository);

        for (var layer : layers) {
            this.filters.put(layer, new ArrayList<>());
        }
    }

    @Override
    public QueryFilter<T> match(String id, Object object) {
        for (var layer : this.filters.keySet()) {
            filters.get(layer).add(layer.filterHandler().match(id, object));
        }
        return this;
    }
}
