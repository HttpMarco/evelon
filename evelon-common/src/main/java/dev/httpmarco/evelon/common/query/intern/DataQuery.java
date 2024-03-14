package dev.httpmarco.evelon.common.query.intern;

import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.query.response.UpdateResponse;
import dev.httpmarco.evelon.common.repository.Repository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import dev.httpmarco.evelon.common.filtering.Filter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Accessors(fluent = true)
public class DataQuery<T> implements Query<T> {

    @Getter
    private final Repository<T> repository;
    private final List<Filter<?, ?>> filters = new ArrayList<>();

    /**
     * Create a specific value on all layers
     * @param value new value
     * @return the update result
     */
    @Override
    public UpdateResponse create(T value) {
        return this.alertLayers(layer -> layer.create(this, value));
    }

    /**
     * Delete all objects with filter accepting value (on all layers)
     * @return the update result
     */
    @Override
    public UpdateResponse deleteAll() {
        return this.alertLayers(layer -> layer.deleteAll(this));
    }

    @Override
    public T findFirst() {
        for (var layer : repository.layers()) {
            if (layer.exists(this).result()) {
                return layer.findFirst(this).result();
            }
        }
        return null;
    }

    @Override
    public boolean exists() {
        for (var layer : repository.layers()) {
            if (layer.exists(this).result()) {
                return true;
            }
        }
        return false;
    }

    private UpdateResponse alertLayers(Function<EvelonLayer<T>, UpdateResponse> function) {
        var response = new UpdateResponse();
        for (var layer : repository.layers()) {
            response.append(function.apply(layer));
        }
        return response.close();
    }
}
