package dev.httpmarco.evelon.common.query.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class QueryResponse<T> extends ResponseResult<QueryResponse<T>> {

    private T result;

    public QueryResponse<T> result(T result) {
        this.result = result;
        return this;
    }
}
