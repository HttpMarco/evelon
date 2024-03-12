package dev.httpmarco.evelon.common.query.response;

import dev.httpmarco.evelon.common.Evelon;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public class ResponseResult<R extends ResponseResult<?>> {

    private ResponseType response = ResponseType.OPEN;
    private final long startTime = System.currentTimeMillis();
    private long processTime = -1;
    private final List<String> errorLog = new ArrayList<>();

    public void append(R response) {
        if (this.response == ResponseType.OPEN || this.response == ResponseType.SUCCESS) {
            this.response = response.response();
        }
        this.errorLog.addAll(response.errorLog());
    }

    public void appendError(String error) {
        this.response = ResponseType.EXCEPTIONAL;
        errorLog.add(error);
    }

    public R close() {
        processTime = System.currentTimeMillis() - startTime;

        if (response != ResponseType.SUCCESS && response != ResponseType.OPEN) {
            Evelon.LOGGER.error("QueryResponse closed: {} - time: {}ms", response, processTime);
            for (var logLine : this.errorLog) {
                Evelon.LOGGER.error("Collected exception: {}", logLine);
            }
        }

        if (response == ResponseType.OPEN) {
            response = ResponseType.SUCCESS;
        }
        return (R) this;
    }

}
