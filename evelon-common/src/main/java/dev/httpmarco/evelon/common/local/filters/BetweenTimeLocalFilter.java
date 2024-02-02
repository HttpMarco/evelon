package dev.httpmarco.evelon.common.local.filters;

import dev.httpmarco.evelon.common.filters.impl.AbstractDateFilter;
import dev.httpmarco.evelon.common.repository.Repository;

import java.util.Date;

public final class BetweenTimeLocalFilter extends AbstractDateFilter<Boolean> {

    private final Date max;

    public BetweenTimeLocalFilter(String id, Date min, Date max) {
        super(id, min);
        this.max = max;
    }

    @Override
    public Boolean filter(Repository<?> repository, Date requiredType) {
        return value().getTime() <= requiredType.getTime() && requiredType.getTime() <= max.getTime();
    }
}
