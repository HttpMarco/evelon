package net.bytemc.evelon.local.filters;

import net.bytemc.evelon.filters.AbstractNumberFilter;
import net.bytemc.evelon.repository.Repository;

public final class BetweenLocalFilter extends AbstractNumberFilter<Boolean> {

    private final Number max;

    public BetweenLocalFilter(String id, Number value, Number max) {
        super(id, value);
        this.max = max;
    }

    @Override
    public Boolean filter(Repository<?> repository, Number requiredType) {
        return getValue().doubleValue() <= requiredType.doubleValue() && requiredType.doubleValue() <= max.doubleValue();
    }
}
