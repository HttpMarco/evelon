package dev.httpmarco.evelon.common.query.response;

import dev.httpmarco.evelon.common.Evelon;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

// information about
@Getter
@Accessors(fluent = true)
public class QueryResponse {

    private boolean closed = false;
    private ResponseType response = ResponseType.OPEN;

    private final long startTime = System.currentTimeMillis();
    private long processTime = -1;

    // only success information
    private int modifiedElements = 0;

    private final List<String> errorLog = new ArrayList<>();

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
        this.errorLog.addAll(response.errorLog);
        this.modifiedElements += response.modifiedElements;
    }

    public void appendError(String error) {
        if (closed) {
            throw new IllegalStateException("QueryResponse already closed");
        }
        this.response = ResponseType.EXCEPTIONAL;
        errorLog.add(error);
    }

    public QueryResponse close() {
        if (closed) {
            throw new IllegalStateException("QueryResponse already closed");
        }
        closed = true;
        processTime = System.currentTimeMillis() - startTime;

        if (response != ResponseType.SUCCESS && response != ResponseType.OPEN) {
            Evelon.LOGGER.error("QueryResponse closed: {} - modified: {} - time: {}ms", response, modifiedElements, processTime);
            for (var logLine : this.errorLog) {
                Evelon.LOGGER.error("Collected exception: {}", logLine);
            }
        }

        if (response == ResponseType.OPEN) {
            response = ResponseType.SUCCESS;
        }
        return this;
    }
}