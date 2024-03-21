package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.UpdateResponse;

@Deprecated
public abstract class DeleteProcess<T> extends Process<T> {

    public DeleteProcess(String id, Query<T> query) {
        super(id, query, false);
    }

    public abstract UpdateResponse pushDelete();

}
