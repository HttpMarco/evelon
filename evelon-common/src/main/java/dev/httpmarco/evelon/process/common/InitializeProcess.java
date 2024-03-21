package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;

@Deprecated //todo
public abstract class InitializeProcess<T> extends Process<T> {

    public InitializeProcess(String id, Query<T> query) {
        super(id, query, true);
    }

    public abstract void pushInitialize();

}