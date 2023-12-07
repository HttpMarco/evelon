package net.bytemc.evelon.local.filters;

import net.bytemc.evelon.filters.AbstractDateFilter;
import net.bytemc.evelon.repository.Repository;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class SameDateLocalFilter extends AbstractDateFilter<Boolean> {

    private final TimeUnit timeUnit;

    public SameDateLocalFilter(String id, Date value, TimeUnit timeUnit) {
        super(id, value);
        this.timeUnit = timeUnit;
    }

    @Override
    public Boolean filter(Repository<?> repository, Date requiredType) {
        long diffInMilliseconds = requiredType.getTime() - getValue().getTime();
        long diff = timeUnit.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
        return diff == 0;
    }
}
