package dev.httpmarco.evelon.process.kind;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.process.ProcessReference;

public abstract class QueryProcess<R extends ProcessReference<R>> extends Process {

    public abstract Object run(RepositoryExternalEntry entry, R reference);

    @SuppressWarnings("unchecked")
    public Object runMapping(RepositoryExternalEntry entry, ProcessReference<?> reference) {
        return this.run(entry, (R) reference);
    }
}
