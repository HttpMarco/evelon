package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.Layer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class RepositoryFilterQuery<T> extends RepositoryQuery<T> implements FilterQuery<T> {

    private final Map<Layer<?>, List<Filter<?, ?>>> filters = new HashMap<>();

    public RepositoryFilterQuery(Repository<T> repository, Layer<?> @NotNull ... layers) {
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
