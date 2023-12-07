package net.bytemc.evelon.filters;

public abstract class AbstractSingleObjectFilter<T> implements Filter<T, Object> {

    private final String id;
    private final Object value;

    public AbstractSingleObjectFilter(String id, Object value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public Object getValue() {
        return value;
    }
}
