package dev.httpmarco.evelon.common.query.intern;

import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.query.response.QueryResponse;
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

    public QueryResponse create(T value) {
        var response = QueryResponse.empty();
        for (var layer : repository.layers()) {
            response.append(layer.create(this, value));
        }
        return response.close();
    }

    @Override
    public QueryResponse deleteAll() {
        var response = QueryResponse.empty();
        for (var layer : repository.layers()) {
            response.append(layer.deleteAll(this));
        }
        return response.close();
    }
}
