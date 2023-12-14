package net.bytemc.evelon.local.filters;

import net.bytemc.evelon.filters.AbstractNumberFilter;
import net.bytemc.evelon.repository.Repository;
import org.jetbrains.annotations.NotNull;

public final class MinimalLocalFilter extends AbstractNumberFilter<Boolean> {

    public MinimalLocalFilter(String id, Number value) {
        super(id, value);
    }

    @Override
    public @NotNull Boolean filter(Repository<?> repository, Number requiredType) {
        return getValue().doubleValue() >= requiredType.doubleValue();
    }
}
