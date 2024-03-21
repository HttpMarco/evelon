package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.filtering.FilterQuery;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public class Query<T> {

    private final Repository<T> repository;

    public UpdateResponse create(T value) {
        var response = new UpdateResponse();
        repository.layers().forEach(layer -> response.append(layer.create(repository, value)));
        return response.close();
    }

    public UpdateResponse deleteAll() {
        var response = new UpdateResponse();
        repository.layers().forEach(layer -> response.append(layer.deleteAll(repository)));
        return response.close();
    }

    public QueryResponse<List<T>> findAll() {
        var response = new QueryResponse<List<T>>();
        repository.layers().forEach(layer -> response.append(layer.findAll(repository)));
        return response.close();
    }

    public boolean exists() {
        var response = new QueryResponse<Boolean>();
        response.result(repository.layers().stream().anyMatch(layer -> layer.exists(repository).result()));
        return response.close().result();
    }

    public FilterQuery<T> filter() {
        return new FilterQuery<>(repository);
    }
}
