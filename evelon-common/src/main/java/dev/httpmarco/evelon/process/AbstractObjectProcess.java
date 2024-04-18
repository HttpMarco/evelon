package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class AbstractObjectProcess<Q> extends AbstractEntryProcess<Q> {

    private final Object value;

    public abstract Q run(RepositoryExternalEntry entry, Object value);

    @Override
    public Q run(@NotNull RepositoryExternalEntry entry) {
        return run(entry, value);
    }
}
