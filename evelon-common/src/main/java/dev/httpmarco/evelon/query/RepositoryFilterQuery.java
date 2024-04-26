package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.Layer;

import java.util.ArrayList;
import java.util.List;


public final class RepositoryFilterQuery<T> extends RepositoryQuery<T> implements FilterQuery<T> {

    private final Layer<?> layer;
    private final List<Filter<?, ?>> filters = new ArrayList<>();

    public RepositoryFilterQuery(Repository<T> repository, Layer<?> layer) {
        super(repository);
        this.layer = layer;
    }

    @Override
    public FilterQuery<T> match(String id, Object object) {
        this.filters.add(this.layer.filterHandler().match(id, object));
        return this;
    }
}
