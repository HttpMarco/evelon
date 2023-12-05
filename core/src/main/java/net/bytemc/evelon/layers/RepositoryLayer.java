package net.bytemc.evelon.layers;

import net.bytemc.evelon.query.DataQuery;

public interface RepositoryLayer {

    /**
     * Creates a new data entry based on the specified query and value.
     *
     * @param query The DataQuery object representing the query for creating the data entry.
     * @param value The value of type T that will be associated with the data entry.
     * @param <T>   The type parameter indicating the class type of the value.
     */
    <T> void create(DataQuery<T> query, T value);

}
