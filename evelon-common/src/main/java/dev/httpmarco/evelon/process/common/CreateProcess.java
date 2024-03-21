package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;

@Deprecated //todo
public abstract class CreateProcess<T> extends Process<T> {

    public CreateProcess(String id, Query<T> query) {
        super(id, query, true);
    }

    public abstract UpdateResponse pushCreate(Object value);

}