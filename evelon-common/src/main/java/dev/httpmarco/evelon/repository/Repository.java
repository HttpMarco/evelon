package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public final class Repository<T> {

    private final RepositoryObjectEntry entry;

    @Contract(value = "_ -> new", pure = true)
    public static <R> @NotNull RepositoryBuilder<R> build(Class<R> clazz) {
        return new RepositoryBuilder<>(clazz);
    }
}
