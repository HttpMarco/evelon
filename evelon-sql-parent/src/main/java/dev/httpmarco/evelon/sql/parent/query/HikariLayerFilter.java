package dev.httpmarco.evelon.sql.parent.query;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.query.layer.LayerFilter;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public final class HikariLayerFilter<T> extends HikariLayerQuery<T> implements LayerFilter<T> {

    public HikariLayerFilter(Layer<?> layer, Repository<T> repository, ProcessRunner<HikariProcessReference> runner) {
        super(layer, repository, runner);
    }

    @Contract(pure = true)
    @Override
    public @Nullable LayerFilter<T> match(String id, Object object) {
        return null;
    }
}
