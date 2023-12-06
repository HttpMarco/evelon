package net.bytemc.evelon.filters;

/**
 * The Filter interface provides methods for creating various filters
 * to be used in data filtering operations.
 */
public interface LayerFilterHandler {

    /**
     * Creates a filter to only include values greater than or equal to the specified minimum.
     *
     * @param id   The identifier for the filter.
     * @param min  The minimum value to filter.
     * @return     A filter object.
     */
    Filter min(String id, int min);

    /**
     * Creates a filter to only include values less than or equal to the specified maximum.
     *
     * @param id   The identifier for the filter.
     * @param max  The maximum value to filter.
     * @return     A filter object.
     */
    Filter max(String id, int max);

    /**
     * Creates a filter to only include values within the specified range (inclusive).
     *
     * @param id   The identifier for the filter.
     * @param min  The minimum value of the range.
     * @param max  The maximum value of the range.
     * @return     A filter object.
     */
    Filter between(String id, int min, int max);

    /**
     * Creates a filter to include values that match the specified value.
     *
     * @param id     The identifier for the filter.
     * @param value  The value to match.
     * @return       A filter object.
     */
    Filter match(String id, Object value);

    /**
     * Creates a filter to exclude values that match any of the specified values.
     *
     * @param id      The identifier for the filter.
     * @param values  The values to exclude.
     * @return        A filter object.
     */
    Filter nonMatch(String id, Object values);

    /**
     * Creates a filter to include values that contain the specified text.
     *
     * @param id     The identifier for the filter.
     * @param value  The text to match.
     * @return       A filter object.
     */
    Filter like(String id, String value);

    // filters for date calculations
    Filter sameDay(String id, String date);

    Filter sameMonth(String id, String date);

    Filter sameYear(String id, String date);

    Filter sameWeek(String id, String date);

    Filter sameQuarter(String id, String date);

    Filter sameHour(String id, String date);

    Filter sameMinute(String id, String date);

    Filter sameSecond(String id, String date);

    Filter sameMillisecond(String id, String date);

    Filter betweenTime(String id, String date1, String date2);

    Filter sameTime(String id, String date);
}
