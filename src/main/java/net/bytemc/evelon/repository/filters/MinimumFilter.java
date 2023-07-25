package net.bytemc.evelon.repository.filters;

import net.bytemc.evelon.local.LocalStorageHelper;
import net.bytemc.evelon.repository.AbstractIdFilter;

import java.util.stream.Stream;

public final class MinimumFilter extends AbstractIdFilter {

    private final Number value;

    public MinimumFilter(String id, Number value) {
        super(id);
        this.value = value;
    }

    @Override
    public String sqlFilter(String id) {
        return id + " >= %s".formatted(value);
    }

    @Override
    public <T> Stream<T> localFilter(Stream<T> stream) {
        return stream.filter(it -> {
            // read field from an object
            var numberFilter = LocalStorageHelper.getNumberFilter(getId(), it);

            return numberFilter == null ? false : numberFilter.doubleValue() >= value.doubleValue();
        });
    }
}
