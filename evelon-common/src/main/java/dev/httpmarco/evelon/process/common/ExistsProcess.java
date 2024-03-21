package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.QueryResponse;

@Deprecated
public abstract class ExistsProcess<T> extends Process<T> {

    public ExistsProcess(String id, Query<T> query) {
        super(id, query, false);
    }

    public abstract QueryResponse<Boolean> queryExists();

}
