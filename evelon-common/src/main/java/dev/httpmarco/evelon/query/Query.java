package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Query<T> {

    private final Repository<T> repository;
    private final Map<Layer, List<Filter<?, ?>>> filters = new HashMap<>();

    public UpdateResponse create(T value) {
        var response = new UpdateResponse();
        repository.layers().forEach(layer -> response.append(layer.create(this, value)));
        return response.close();
    }

    public UpdateResponse deleteAll() {
        var response = new UpdateResponse();
        repository.layers().forEach(layer -> response.append(layer.deleteAll(this)));
        return response.close();
    }

    public QueryResponse<List<T>> findAll() {
        var response = new QueryResponse<List<T>>();
        repository.layers().forEach(layer -> response.append(layer.findAll(this)));
        return response.close();
    }

    public boolean exists() {
        var response = new QueryResponse<Boolean>();
        response.result(repository.layers().stream().anyMatch(layer -> layer.exists(this).result()));
        return response.close().result();
    }

    public Filters<T> filter() {
        return new Filters<>(repository);
    }

    public List<Filter<?, ?>> filters(Layer layer) {
        return filters.getOrDefault(layer, List.of());
    }


    public static class Filters<T> extends Query<T> {

        public Filters(Repository<T> repository) {
            super(repository);
        }

        public Filters<T> match(String id, Object o) {
            for (Layer layer : repository().layers()) {
                var filterList = filters().getOrDefault(layer, new LinkedList<>());
                filterList.add(layer.filterHandler().match(id, o));
                filters().put(layer, filterList);
            }
            return this;
        }

        public Filters<T> noneMatch(String id, Object o) {
            for (Layer layer : repository().layers()) {
                var filterList = filters().getOrDefault(layer, new LinkedList<>());
                filterList.add(layer.filterHandler().noneMatch(id, o));
                filters().put(layer, filterList);
            }
            return this;
        }

        public Filters<T> like(String id, String o) {
            for (Layer layer : repository().layers()) {
                var filterList = filters().getOrDefault(layer, new LinkedList<>());
                filterList.add(layer.filterHandler().like(id, o));
                filters().put(layer, filterList);
            }
            return this;
        }
    }
}