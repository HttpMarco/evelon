package net.bytemc.evelon.filters;

public abstract class AbstractTextFilter<T> implements Filter<T, String> {

    private final String id;
    private final String value;

    public AbstractTextFilter(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        return clazz.equals(String.class);
    }
}
