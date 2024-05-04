package dev.httpmarco.evelon.process.kind;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.process.ProcessReference;

public abstract class UpdateProcess<R extends ProcessReference<R>> extends Process {

     public abstract void run(RepositoryExternalEntry entry, R reference);

     @SuppressWarnings("unchecked")
     public void runMapping(RepositoryExternalEntry entry, ProcessReference<?> reference) {
            run(entry, (R) reference);
     }
}
