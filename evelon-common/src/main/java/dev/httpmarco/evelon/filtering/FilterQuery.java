package dev.httpmarco.evelon.filtering;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.repository.Repository;

import java.util.*;

public class FilterQuery<T> extends Query<T> {

    private final Map<Layer, List<Filter<?, ?>>> filters = new HashMap<>();

    public FilterQuery(Repository<T> repository) {
        super(repository);
    }

    public FilterQuery<T> match(String id, Object o) {
        for (Layer layer : repository().layers()) {
            var filterList = filters.getOrDefault(layer, new LinkedList<>());
            filterList.add(layer.filterHandler().match(id, o));
            filters.put(layer, filterList);
        }
        return this;
    }
}
