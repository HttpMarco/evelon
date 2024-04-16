package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractEntryProcess<Q, P extends Process<Q, P>> extends Process<Q, P> {

    public abstract Q run(@NotNull RepositoryExternalEntry entry);

}
