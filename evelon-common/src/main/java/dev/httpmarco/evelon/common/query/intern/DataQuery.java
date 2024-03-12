package dev.httpmarco.evelon.common.query.intern;

import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.query.response.UpdateResponse;
import dev.httpmarco.evelon.common.repository.Repository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import dev.httpmarco.evelon.common.filters.Filter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Accessors(fluent = true)
public class DataQuery<T> implements Query<T> {

    @Getter
    private final Repository<T> repository;
    private final List<Filter<?, ?>> filters = new ArrayList<>();

    public UpdateResponse create(T value) {
        var response = new UpdateResponse();
        for (var layer : repository.layers()) {
            response.append(layer.create(this, value));
        }
        return response.close();
    }

    @Override
    public UpdateResponse deleteAll() {
        var response = new UpdateResponse();
        for (var layer : repository.layers()) {
            response.append(layer.deleteAll(this));
        }
        return response.close();
    }

    @Override
    public T findFirst() {
        for (var layer : repository.layers()) {
            if (layer.exists(this).result()) {
                //todo
                return null;
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
}
