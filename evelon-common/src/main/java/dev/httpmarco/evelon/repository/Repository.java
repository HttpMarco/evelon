package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class Repository<T> {

    private final RepositoryObjectEntry entry;
    private final Set<Layer<?>> abstractLayers;

    @Contract(value = "_ -> new", pure = true)
    public static <R> @NotNull RepositoryBuilder<R> build(Class<R> clazz) {
        return new RepositoryBuilder<>(clazz);
    }
}
