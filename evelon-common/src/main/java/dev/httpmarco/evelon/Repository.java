package dev.httpmarco.evelon;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.query.layer.LayerQuery;
import dev.httpmarco.evelon.external.RepositoryObjectEntry;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.common.RepositoryQuery;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record Repository<T>(RepositoryObjectEntry entry, Set<Layer<?>> layers) {

    @Contract(value = "_ -> new", pure = true)
    public static <R> @NotNull RepositoryBuilder<R> build(Class<R> clazz) {
        return new RepositoryBuilder<>(clazz);
    }

    @Contract(" -> new")
    public @NotNull Query<T> query() {
        return new RepositoryQuery<>(this);
    }

    public @NotNull LayerQuery<T> query(Class<? extends Layer<?>> layer) {
        return layers.stream().filter(it -> it.getClass().equals(layer)).findFirst().orElseThrow().query(this);
    }
}
