package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import dev.httpmarco.evelon.repository.exception.RepositoryTypeNotAllowedException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class RepositoryBuilder<T> {

    private final Class<T> clazz;
    private String id;

    @Contract(pure = true)
    public RepositoryBuilder(@NotNull Class<T> clazz) {
        this.clazz = clazz;
        this.id = clazz.getSimpleName();
    }

    public RepositoryBuilder<T> withId(String id) {
        this.id = id;
        return this;
    }

    @Contract(" -> new")
    public @NotNull Repository<T> build() {
        var entry = RepositoryEntryType.scan(clazz).generation().generate(id, clazz, null);

        if (entry instanceof RepositoryObjectEntry objectEntry) {
            return new Repository<>(objectEntry);
        }
        throw new RepositoryTypeNotAllowedException();
    }
}
