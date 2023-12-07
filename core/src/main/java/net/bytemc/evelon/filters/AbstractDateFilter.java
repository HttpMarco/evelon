package net.bytemc.evelon.filters;

import java.util.Date;

public abstract class AbstractDateFilter<T> implements Filter<T, Date> {

    private final String id;
    private final Date value;

    public AbstractDateFilter(String id, Date value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public Date getValue() {
        return value;
    }
}
