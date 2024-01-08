package dev.httpmarco.evelon.common.layers;

import dev.httpmarco.evelon.common.filters.LayerFilterHandler;
import dev.httpmarco.evelon.common.misc.SortedOrder;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.query.DataQuery;

import java.util.List;
import java.util.function.Predicate;

public abstract class RepositoryLayer {

    private final LayerFilterHandler<?, ?> filterHandler;
    private final Model model;

    public RepositoryLayer(LayerFilterHandler<?, ?> filterHandler, Model model) {
        this.filterHandler = filterHandler;
        this.model = model;
    }

    /**
     * Creates a new data entry based on the specified query and value.
     *
     * @param query The DataQuery object representing the query for creating the data entry.
     * @param value The value of type T that will be associated with the data entry.
     * @param <T>   The type parameter indicating the class type of the value.
     */
    public abstract <T> void create(DataQuery<T> query, T value);

    /**
     * Creates a new entry in the data store if it does not already exist.
     *
     * @param <T>   the type of data to be stored
     * @param query the {@code DataQuery} used to check for the existence of the entry
     * @param value the value to be stored if the entry does not exist
     */
    public abstract <T> void createIfNotExists(DataQuery<T> query, T value);

    /**
     * Deletes data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for deleting data entries.
     * @param <T>   The type parameter indicating the class type of the data entries.
     */
    public abstract <T> void deleteAll(DataQuery<T> query);

    /**
     * Deletes data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for deleting data entries.
     * @param value The value of type T that will be associated with the data entry.
     * @param <T>   The type parameter indicating the class type of the data entries.
     */
    public abstract <T> void delete(DataQuery<T> query, T value);

    /**
     * Updates existing data entries based on the specified query and new value.
     *
     * @param query The DataQuery object representing the query for updating data entries.
     * @param value The new value of type T that will replace the existing value.
     * @param <T>   The type parameter indicating the class type of the value.
     */
    public abstract <T> void update(DataQuery<T> query, T value);

    /**
     * Updates existing data entries based on the specified query and new value.
     *
     * @param query     The DataQuery object representing the query for updating data entries.
     * @param value     The new value of type T that will replace the existing value.
     * @param predicate The value will only be updated if the specified predicate returns true.
     * @param <T>       The type parameter indicating the class type of the value.
     */
    public abstract <T> void updateIf(DataQuery<T> query, T value, Predicate<T> predicate);

    /**
     * Updates or inserts a new data entry based on the specified query and value.
     *
     * @param query The DataQuery object representing the query for upserting data entries.
     * @param value The value of type T that will be associated with the data entry.
     * @param <T>   The type parameter indicating the class type of the value.
     */
    public abstract <T> void upsert(DataQuery<T> query, T value);

    /**
     * Finds all data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for finding data entries.
     * @param <T>   The type parameter indicating the class type of the data entries.
     * @return A List containing all data entries that match the query.
     */
    public abstract <T> List<T> findAll(DataQuery<T> query);

    /**
     * Finds all data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for finding data entries.
     * @param <T>   The type parameter indicating the class type of the data entries.
     * @param limit The maximum number of data entries to retrieve.
     * @return A List containing all data entries that match the query.
     */
    public abstract <T> List<T> findAll(DataQuery<T> query, int limit);

    /**
     * Finds a single data entry based on the specified query.
     *
     * @param query The DataQuery object representing the query for finding a data entry.
     * @param <T>   The type parameter indicating the class type of the data entry.
     * @return The data entry that matches the query, or null if no match is found.
     */
    public abstract <T> T find(DataQuery<T> query);

    /**
     * Checks if any data entries exist based on the specified query.
     *
     * @param query The DataQuery object representing the query for checking the existence of data entries.
     * @param <T>   The type parameter indicating the class type of the data entries.
     * @return True if at least one data entry exists, otherwise false.
     */
    public abstract <T> boolean exists(DataQuery<T> query);

    /**
     * Counts the number of data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for counting data entries.
     * @param <T>   The type parameter indicating the class type of the data entries.
     * @return The number of data entries that match the query.
     */
    public abstract <T> long count(DataQuery<T> query);

    /**
     * Calculates the sum of a numeric property (specified by 'id') of data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for summing data entries.
     * @param id    The identifier of the numeric property to be summed.
     * @param <T>   The type parameter indicating the class type of the data entries.
     * @return The sum of the specified numeric property for data entries that match the query.
     */
    public abstract <T> long sum(DataQuery<T> query, String id);

    /**
     * Calculates the average of a numeric property (specified by 'id') of data entries based on the specified query.
     *
     * @param query The DataQuery object representing the query for averaging data entries.
     * @param id    The identifier of the numeric property for which the average is calculated.
     * @param <T>   The type parameter indicating the class type of the data entries.
     * @return The average of the specified numeric property for data entries that match the query.
     */
    public abstract <T> double avg(DataQuery<T> query, String id);

    /**
     * Orders and retrieves a specified maximum number of data entries based on the specified query and sorting criteria.
     *
     * @param query The DataQuery object representing the query for ordering data entries.
     * @param id    The identifier of the property by which data entries are sorted.
     * @param limit The maximum number of data entries to retrieve.
     * @param order The sorting order (ascending or descending).
     * @param <T>   The type parameter indicating the class type of the data entries.
     * @return A List containing the ordered data entries up to the specified maximum.
     */
    public abstract <T> List<T> order(DataQuery<T> query, String id, int limit, SortedOrder order);

    public abstract <E, T> List<E> collect(DataQuery<T> query, String id, Class<E> clazz);

    public abstract <E, T> List<E> collect(DataQuery<T> query, String id, int limit, Class<E> clazz);

    public abstract <E, T> E collectSingle(DataQuery<T> query, String id, Class<E> clazz);

    public abstract <T> T max(DataQuery<T> query, String id);

    public abstract <T> T min(DataQuery<T> query, String id);

    /**
     * Gets the filter handler for this layer.
     *
     * @return The filter handler.
     */
    public LayerFilterHandler<?, ?> filterHandler() {
        return this.filterHandler;
    }

    public Model model() {
        return this.model;
    }
}
