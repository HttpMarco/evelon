package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractEntryProcess<Q> extends Process {

    public abstract Q run(@NotNull RepositoryExternalEntry entry);

}
