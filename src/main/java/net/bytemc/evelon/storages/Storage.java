package net.bytemc.evelon.storages;

import net.bytemc.evelon.repository.RepositoryQuery;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Storage {

    /**
     * @param query a query to the specific repository
     * @param value an object from the specific repository
     * @param <T> repository type
     */
    <T> void create(RepositoryQuery<T> query, T value);

    /**
     * @param query
     * @param <T>
     */
    <T> void delete(RepositoryQuery<T> query);

    /**
     * @param query
     * @param <T>
     * @return
     */
    <T> List<T> findAll(RepositoryQuery<T> query);

    /**
     * @param query a query to the specific repository
     * @param <T>
     * @return
     */
    <T> @Nullable T findFirst(RepositoryQuery<T> query);

    /**
     * @param query
     * @param value
     * @param <T>
     */
    <T> void update(RepositoryQuery<T> query, T value);

    <T> boolean exists(RepositoryQuery<T> query);

    <T> int count(RepositoryQuery<T> query);

    default <T> void createIfNotExists(RepositoryQuery<T> query, T value) {
        if(!exists(query)) {
            create(query, value);
        }
    }
}
