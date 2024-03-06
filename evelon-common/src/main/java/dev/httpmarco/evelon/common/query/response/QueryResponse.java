package dev.httpmarco.evelon.common.query.response;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

// information about
@Setter
@Accessors(fluent = true)
public class QueryResponse {

    private ResponseType response = ResponseType.OPEN;

    private final long startTime = System.currentTimeMillis();
    private long processTime = -1;

    // only success information
    private int modifiedElements = 0;

    public static QueryResponse empty() {
        return new QueryResponse();
    }
}