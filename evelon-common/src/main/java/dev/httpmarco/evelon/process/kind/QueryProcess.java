package dev.httpmarco.evelon.process.kind;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.process.ProcessReference;

public abstract class QueryProcess<R extends ProcessReference<? extends Connection<?, ?>>, F extends Filter<?, ?>> extends Process<F> {

    public abstract Object run(RepositoryExternalEntry entry, R reference);

    @SuppressWarnings("unchecked")
    public Object runMapping(RepositoryExternalEntry entry, ProcessReference<?> reference) {
        return this.run(entry, (R) reference);
    }
}
