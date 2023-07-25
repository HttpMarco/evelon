package net.bytemc.evelon.repository.filters;

import net.bytemc.evelon.local.LocalStorageHelper;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.AbstractIdFilter;
import java.util.stream.Stream;

public final class BetweenFilter extends AbstractIdFilter {

    private final Object minimumBounce;
    private final Object maximumBounce;

    public BetweenFilter(String id, Object minimumBounce, Object maximumBounce) {
        super(id);

        if(!Reflections.isNumber(minimumBounce.getClass()) || !Reflections.isNumber(maximumBounce.getClass())) {
            throw new IllegalArgumentException("The minimumBounce and maximumBounce must be a number.");
        }

        this.minimumBounce = minimumBounce;
        this.maximumBounce = maximumBounce;
    }

    @Override
    public String sqlFilter(String id) {
        return id + " BETWEEN " + minimumBounce + " AND " + maximumBounce;
    }

    @Override
    public <T> Stream<T> localFilter(Stream<T> stream) {
        // maybe we must check every fall of number types and not only the doubles.
        return stream.filter(it -> {
            // read field from an object
            var numberFilter = LocalStorageHelper.getNumberFilter(getId(), it);

            return numberFilter == null ? false : numberFilter.doubleValue() >= ((Number) minimumBounce).doubleValue() && numberFilter.doubleValue() <= ((Number) maximumBounce).doubleValue();
        });
    }
}
