package dev.httpmarco.evelon.common.builder;

import dev.httpmarco.evelon.common.query.response.QueryResponse;
import dev.httpmarco.evelon.common.query.response.UpdateResponse;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public interface Builder<B extends Builder<B, A, D>, A, D> {

    @Nullable
    B parent();

    List<B> children();

    void appendValue(Object value);

    void linkPrimaries(Set<PrimaryRepositoryFieldImpl<?>> fields);

    B subBuilder(String subId);

    B subBuilder(String subId, RepositoryClass<?> parent);

    UpdateResponse update(A arg);

    <T> QueryResponse<T> query(A arg, BuilderTransformer<D, T> function, T defaultValue);

}
