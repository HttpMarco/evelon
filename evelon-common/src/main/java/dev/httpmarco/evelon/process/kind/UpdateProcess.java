package dev.httpmarco.evelon.process.kind;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.process.ProcessReference;

public abstract class UpdateProcess<R extends ProcessReference<? extends Connection<?, ?>>, F extends Filter<?, ?>> extends Process<F> {

    public abstract void run(RepositoryExternalEntry entry, R reference);

    @SuppressWarnings("unchecked")
    public void runMapping(RepositoryExternalEntry entry, ProcessReference<?> reference) {
        run(entry, (R) reference);
    }
}
