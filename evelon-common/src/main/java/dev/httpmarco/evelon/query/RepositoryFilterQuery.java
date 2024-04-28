package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.Layer;
import java.util.*;


public final class RepositoryFilterQuery<T> extends RepositoryQuery<T> implements FilterQuery<T> {

    private final Map<Layer<?>, List<Filter<?, ?>>> filters = new HashMap<>();

    public RepositoryFilterQuery(Repository<T> repository, Set<Layer<?>> layers) {
        super(repository);

        for (var layer : layers) {
            this.filters.put(layer, new ArrayList<>());
        }
    }

    @Override
    public FilterQuery<T> match(String id, Object object) {
        for (Layer<?> layer : this.filters.keySet()) {
            filters.get(layer).add(layer.filterHandler().match(id, object));
        }
        return this;
    }
}
