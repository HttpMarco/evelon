package net.bytemc.evelon.local.filters;

import net.bytemc.evelon.filters.AbstractDateFilter;
import net.bytemc.evelon.repository.Repository;

import java.util.Date;

public final class BetweenTimeLocalFilter extends AbstractDateFilter<Boolean> {

    private final Date max;

    public BetweenTimeLocalFilter(String id, Date min, Date max) {
        super(id, min);
        this.max = max;
    }

    @Override
    public Boolean filter(Repository<?> repository, Date requiredType) {
        return getValue().getTime() <= requiredType.getTime() && requiredType.getTime() <= max.getTime();
    }
}
