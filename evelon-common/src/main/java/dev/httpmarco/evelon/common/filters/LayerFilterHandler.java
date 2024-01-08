package dev.httpmarco.evelon.common.filters;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * The Filter interface provides methods for creating various filters
 * to be used in data filtering operations.
 */
public interface LayerFilterHandler<T, R> {

    /**
     * Creates a filter to only include values greater than or equal to the specified minimum.
     *
     * @param id  The identifier for the filter.
     * @param min The minimum value to filter.
     * @return A filter object.
     */
    Filter<T, Number> min(String id, int min);

    /**
     * Creates a filter to only include values less than or equal to the specified maximum.
     *
     * @param id  The identifier for the filter.
     * @param max The maximum value to filter.
     * @return A filter object.
     */
    Filter<T, Number> max(String id, int max);

    /**
     * Creates a filter to only include values within the specified range (inclusive).
     *
     * @param id  The identifier for the filter.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return A filter object.
     */
    Filter<T, Number> between(String id, int min, int max);

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
    Filter<T, String> like(String id, String value);

    // filters for date calculations
    Filter<T, Date> sameDate(String id, Date date, TimeUnit timeUnit);

    Filter<T, Date> betweenTime(String id, Date minDate, Date maxDate);

    Filter<T, Date> sameTime(String id, Date date);
}
