package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractEntryProcess<R extends ProcessReference<R>> extends Process {

    public abstract void run(@NotNull RepositoryExternalEntry entry, R reference);

}
