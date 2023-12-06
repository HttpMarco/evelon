package net.bytemc.evelon.local.filters;

import net.bytemc.evelon.filters.AbstractNumberFilter;
import net.bytemc.evelon.repository.Repository;

public final class MaximalLocalFilter extends AbstractNumberFilter<Boolean> {

    public MaximalLocalFilter(String id, Number value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Number requiredType) {
        return getValue().doubleValue() <= requiredType.doubleValue();
    }
}
