package dev.httpmarco.evelon.common.local.filtering;

import dev.httpmarco.evelon.common.filtering.common.AbstractNumberFilter;
import dev.httpmarco.evelon.common.repository.Repository;

public final class BetweenLocalFilter extends AbstractNumberFilter<Boolean> {

    private final Number max;

    public BetweenLocalFilter(String id, Number value, Number max) {
        super(id, value);
        this.max = max;
    }

    @Override
    public Boolean filter(Repository<?> repository, Number requiredType) {
        return value().doubleValue() <= requiredType.doubleValue() && requiredType.doubleValue() <= max.doubleValue();
    }
}
