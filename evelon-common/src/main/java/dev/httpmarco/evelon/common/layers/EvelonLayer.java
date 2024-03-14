package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.filtering.LayerFilterHandler;
import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.query.SortedOrder;
import dev.httpmarco.evelon.common.query.response.QueryResponse;
import dev.httpmarco.evelon.common.query.response.UpdateResponse;

import java.util.List;
import java.util.function.Predicate;

public interface EvelonLayer<T> extends EvelonLayerSession {

    /**
     * Gets the id of the layer.
     * @return the id of the layer
     */
    String id();

    /**
     * Creates a new data entry based on the specified query and value.
     *
     * @param query The DataQuery object representing the query for creating the data entry.
     * @param value The value of type T that will be associated with the data entry.
     */
    UpdateResponse create(Query<T> query, T value);

    /**
     * Creates a new entry in the data store if it does not already exist.
     *
     * @param query the {@code DataQuery} used to check for the existence of the entry
     * @param value the value to be stored if the entry does not exist
     */
    void createIfNotExists(Query<T> query, T value);

    /**
     * Deletes data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for deleting data entries.
     */
    UpdateResponse deleteAll(Query<T> query);

    /**
     * Deletes data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for deleting data entries.
     * @param value The value of type T that will be associated with the data entry.
     */
    void delete(Query<T> query, T value);

    /**
     * Updates existing data entries based on the specified query and new value.
     *
     * @param query The DataQuery object representing the query for updating data entries.
     * @param value The new value of type T that will replace the existing value.
     */
    void update(Query<T> query, T value);

    /**
     * Updates existing data entries based on the specified query and new value.
     *
     * @param query     The DataQuery object representing the query for updating data entries.
     * @param value     The new value of type T that will replace the existing value.
     * @param predicate The value will only be updated if the specified predicate returns true.
     */
    void updateIf(Query<T> query, T value, Predicate<T> predicate);

    /**
     * Updates or inserts a new data entry based on the specified query and value.
     *
     * @param query The DataQuery object representing the query for upserting data entries.
     * @param value The value of type T that will be associated with the data entry.
     */
    void upsert(Query<T> query, T value);

    /**
     * Finds all data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for finding data entries.
     * @return A List containing all data entries that match the query.
     */
    List<T> findAll(Query<T> query);

    /**
     * Finds all data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for finding data entries.
     * @param limit The maximum number of data entries to retrieve.
     * @return A List containing all data entries that match the query.
     */
    List<T> findAll(Query<T> query, int limit);

    /**
     * Finds a single data entry based on the specified query.
     *
     * @param query The DataQuery object representing the query for finding a data entry.
     * @return The data entry that matches the query, or null if no match is found.
     */
    QueryResponse<T> findFirst(Query<T> query);

    /**
     * Checks if any data entries exist based on the specified query.
     *
     * @param query The DataQuery object representing the query for checking the existence of data entries.
     * @return True if at least one data entry exists, otherwise false.
     */
    QueryResponse<Boolean> exists(Query<T> query);

    /**
     * Counts the number of data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for counting data entries.
     * @return The number of data entries that match the query.
     */
    long count(Query<T> query);

    /**
     * Calculates the sum of a numeric property (specified by 'id') of data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for summing data entries.
     * @param id    The identifier of the numeric property to be summed.
     * @return The sum of the specified numeric property for data entries that match the query.
     */
    long sum(Query<T> query, String id);

    /**
     * Calculates the average of a numeric property (specified by 'id') of data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for averaging data entries.
     * @param id    The identifier of the numeric property for which the average is calculated.
     * @return The average of the specified numeric property for data entries that match the query.
     */
    double avg(Query<T> query, String id);

    /**
     * Orders and retrieves a specified maximum number of data entries based on the specified query and sorting criteria.
     *
     * @param query The DataQuery object representing the query for ordering data entries.
     * @param id    The identifier of the property by which data entries are sorted.
     * @param limit The maximum number of data entries to retrieve.
     * @param order The sorting order (ascending or descending).
     * @return A List containing the ordered data entries up to the specified maximum.
     */
    List<T> order(Query<T> query, String id, int limit, SortedOrder order);

    <E> List<E> collect(Query<T> query, String id, Class<E> clazz);

    <E> List<E> collect(Query<T> query, String id, int limit, Class<E> clazz);

    <E> E collectSingle(Query<T> query, String id, Class<E> clazz);

    T max(Query<T> query, String id);

    T min(Query<T> query, String id);

    /**
     * Gets the filter handler for this layer.
     *
     * @return The filter handler.
     */
    LayerFilterHandler<?, ?> filterHandler();

}
