package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class AbstractObjectProcess<R extends ProcessReference<R>> extends AbstractEntryProcess<R> {

    private final Object value;

    public abstract void run(@NotNull RepositoryExternalEntry entry, Object value, R reference);

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, R reference) {
        run(entry, value, reference);
    }
}
