package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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

}
