package net.bytemc.evelon.repository.filters;

import net.bytemc.evelon.local.LocalStorageHelper;
import net.bytemc.evelon.repository.AbstractIdFilter;

import java.util.stream.Stream;

public final class MatchFilter extends AbstractIdFilter {

    private final Object value;

    public MatchFilter(String id, Object value) {
        super(id);
        this.value = value;
    }

    @Override
    public String sqlFilter(String id) {
        return "%s = '%s'".formatted(getId(), value.toString());
    }

    @Override
    public <T> Stream<T> localFilter(Stream<T> stream) {
        return stream.filter(it -> LocalStorageHelper.getObjectFilter(it.getClass(), getId(), it).equals(value));
    }
}
