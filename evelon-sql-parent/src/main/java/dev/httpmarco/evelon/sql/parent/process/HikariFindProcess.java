package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractEntryProcess;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.HikariExecutionReference;
import org.jetbrains.annotations.NotNull;

public class HikariFindProcess extends AbstractEntryProcess<HikariExecutionReference> {

    @Override
    public HikariExecutionReference run(@NotNull RepositoryExternalEntry entry) {
        return null;
    }
}
