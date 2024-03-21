package dev.httpmarco.evelon.filtering;

public interface LayerFilterHandler<T, R> {

    /**
     * Creates a filter to include values that match the specified value.
     *
     * @param id    The identifier for the filter.
     * @param value The value to match.
     * @return A filter object.
     */
    Filter<T, R> match(String id, Object value);

}
