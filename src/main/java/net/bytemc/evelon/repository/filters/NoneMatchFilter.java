package net.bytemc.evelon.repository.filters;

import net.bytemc.evelon.local.LocalStorageHelper;
import net.bytemc.evelon.repository.AbstractIdFilter;
import net.bytemc.evelon.sql.Schema;

import java.util.stream.Stream;

public final class NoneMatchFilter extends AbstractIdFilter {

    private final Object value;

    public NoneMatchFilter(String id, Object value) {
        super(id);
        this.value = value;
    }

    @Override
    public String sqlFilter(String id) {
        return ("%s != " + Schema.encloseSchema("%s")).formatted(getId(), value.toString());
    }

    @Override
    public <T> Stream<T> localFilter(Stream<T> stream) {
        return stream.filter(it -> !LocalStorageHelper.getObjectFilter(getId(), it).equals(value));
    }
}
