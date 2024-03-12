package dev.httpmarco.evelon.common.builder;

import dev.httpmarco.evelon.common.query.response.QueryResponse;
import dev.httpmarco.evelon.common.query.response.UpdateResponse;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public interface Builder<B extends Builder<B, A, D>, A, D> {

    /**
     * Get the parent if exists
     * @return the builder parent
     */
    @Nullable
    B parent();

    /**
     * Get the children of the builder
     * @return the children of the builder
     */
    List<B> children();

    /**
     * Add a new value in query
     */
    void appendValue(Object value);

    /**
     * Add the primaries from parent
     * @param fields a list with all the primary fields
     */
    void linkPrimaries(Set<PrimaryRepositoryFieldImpl<?>> fields);

    /**
     * @param subId id of the current sub builder
     * @return a new sub builder
     */
    B subBuilder(String subId);

    /**
     * Create a new sub builder and linking directly the parent
     * @param subId id of the current sub builder
     * @param parent the parent of the sub builder
     * @return a new sub builder
     */
    B subBuilder(String subId, RepositoryClass<?> parent);

    /**
     * Send update query to database interface
     * @param arg the argument to update
     * @return the result
     */
    UpdateResponse update(A arg);

    /**
     *
     * @param arg the argument to query
     * @param function data transformer
     * @param defaultValue if an exception occurs
     * @return the result info
     * @param <T> Type of value
     */
    <T> QueryResponse<T> query(A arg, BuilderTransformer<D, T> function, T defaultValue);

}
