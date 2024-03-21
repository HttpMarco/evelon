package dev.httpmarco.evelon.query.response;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class QueryResponse<T> extends ResponseResult<QueryResponse<T>> {

    private T result;

    public QueryResponse<T> result(T result) {
        this.result = result;
        return this;
    }


    @Override
    public void append(QueryResponse<T> response) {
        super.append(response);
        this.result = response.result;
    }
}