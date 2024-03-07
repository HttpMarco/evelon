package dev.httpmarco.evelon.common.query.response;

import lombok.Setter;
import lombok.experimental.Accessors;

// information about
@Setter
@Accessors(fluent = true)
public class QueryResponse {

    private boolean closed = false;
    private ResponseType response = ResponseType.OPEN;

    private final long startTime = System.currentTimeMillis();
    private long processTime = -1;

    // only success information
    private int modifiedElements = 0;

    public static QueryResponse empty() {
        return new QueryResponse();
    }

    public void append(QueryResponse response) {
        if (closed) {
            throw new IllegalStateException("QueryResponse already closed");
        }

        if (this.response == ResponseType.OPEN || this.response == ResponseType.SUCCESS) {
            this.response = response.response;
        }
        this.modifiedElements += response.modifiedElements;
    }

    public QueryResponse close() {
        if (closed) {
            throw new IllegalStateException("QueryResponse already closed");
        }
        closed = true;
        processTime = System.currentTimeMillis() - startTime;
        return this;
    }
}