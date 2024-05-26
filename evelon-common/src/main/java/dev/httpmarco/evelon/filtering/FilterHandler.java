package dev.httpmarco.evelon.filtering;

public interface FilterHandler<T, R> {

    /**
     * Creates a filter to include values that match the specified value.
     *
     * @param id    The identifier for the filter.
     * @param value The value to match.
     * @return A filter object.
     */
    Filter<T, R> match(String id, Object value);

    /**
     * Creates a filter to exclude values that match any of the specified values.
     *
     * @param id     The identifier for the filter.
     * @param value The values to exclude.
     * @return A filter object.
     */
    Filter<T, R> noneMatch(String id, Object value);

    /**
     * Creates a filter to include values that contain the specified text.
     *
     * @param id    The identifier for the filter.
     * @param value The text to match.
     * @return A filter object.
     */
    Filter<T, R> like(String id, String value);

    /**
     * Creates a filter to include values that match the specified value and ignore upper or lower case.
     *
     * @param id    The identifier for the filter.
     * @param value The value to match.
     * @return A filter object.
     */
    Filter<T, R> matchIgnoreCase(String id, String value);

    Filter<T, R> between(String id, Number min, Number max);

    Filter<T, R> min(String id, Number min);

    Filter<T, R> max(String id, Number max);

}
