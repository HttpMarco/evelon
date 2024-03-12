package dev.httpmarco.evelon.common.query.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public final class QueryResponse<T> extends ResponseResult {

    private T result;

}
