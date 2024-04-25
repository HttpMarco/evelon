package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.Layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RepositoryFilterQuery<T> extends RepositoryQuery<T> implements FilterQuery<T> {

    private final Map<Layer<?>, List<Filter<?, ?>>> filters = new HashMap<>();

    public RepositoryFilterQuery(Repository<T> repository) {
        super(repository);
    }

    @Override
    public FilterQuery<T> match(String id, Object object) {
        for (var layer : repository().layers()) {
            filters.computeIfAbsent(layer, k -> new ArrayList<>()).add(layer.filterHandler().match(id, object));
        }
        return this;
    }
}
