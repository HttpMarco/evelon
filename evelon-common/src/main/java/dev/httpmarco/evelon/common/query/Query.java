package dev.httpmarco.evelon.common.query;

import dev.httpmarco.evelon.common.filtering.Filter;
import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.layers.EvelonModelLayer;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.query.response.UpdateResponse;
import dev.httpmarco.evelon.common.repository.Repository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.function.Function;

@RequiredArgsConstructor
@Accessors(fluent = true)
public class Query<T> {

    @Getter
    private final Repository<T> repository;
    protected final Map<Model, Filter<?, ?>> filters = new LinkedHashMap<>();

    /**
     * Create a specific value on all layers
     *
     * @param value new value
     * @return the update result
     */
    public UpdateResponse create(T value) {
        return this.alertLayers(layer -> layer.create(this, value));
    }

    /**
     * Delete all objects with filter accepting value (on all layers)
     *
     * @return the update result
     */
    public UpdateResponse deleteAll() {
        return this.alertLayers(layer -> layer.deleteAll(this));
    }

    /**
     * Find first searched element
     *
     * @return the object or null
     */
    public T findFirst() {
        var layer = firstLayer();
        return layer == null ? null : layer.findFirst(this).result();
    }

    /**
     * show all objects with filter accepting value (on all layers)
     *
     * @return if exists
     */
    public boolean exists() {
        return firstLayer() != null;
    }

    /**
     * Alert a specific action on all layers
     *
     * @param function describe what to do with the layer
     * @return the response
     */
    private UpdateResponse alertLayers(Function<EvelonLayer<T>, UpdateResponse> function) {
        var response = new UpdateResponse();
        repository.layers().forEach(layer -> response.append(function.apply(layer)));
        return response.close();
    }

    /**
     * Search for first layer, which has the object
     *
     * @return the layer or null
     */
    public EvelonLayer<T> firstLayer() {
        return repository.layers().stream().filter(it -> it.exists(this).result()).findFirst().orElse(null);
    }

    public FilterQuery<T> filter() {
        return new FilterQuery<T>(this);
    }

    public static class FilterQuery<T> extends Query<T> {

        protected FilterQuery(Query<T> behavorQuery) {
            super(behavorQuery.repository);
            this.filters.putAll(behavorQuery.filters);
        }

        public FilterQuery<T> match(String id, Object value) {
            repository().layers().stream()
                    //todo remove this if model layers not exists
                    .map(it -> ((EvelonModelLayer<?>) it))
                    .forEach(it -> filters.put(it.model(), it.filterHandler().match(id, value)));
            return this;
        }
    }
}

