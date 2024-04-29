package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.Layer;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public final class RepositoryFilterQuery<T> extends RepositoryQuery<T> implements FilterQuery<T> {

    private final Map<Layer<?>, List<Filter<?, ?>>> filters = new HashMap<>();

    public RepositoryFilterQuery(Repository<T> repository, @NotNull Set<Layer<?>> layers) {
        super(repository);

        for (var layer : layers) {
            this.filters.put(layer, new ArrayList<>());
        }
    }

    @Override
    public FilterQuery<T> match(String id, Object object) {
        for (var layer : this.filters.keySet()) {
            filters.get(layer).add(layer.filterHandler().match(id, object));
        }
        return this;
    }
}
