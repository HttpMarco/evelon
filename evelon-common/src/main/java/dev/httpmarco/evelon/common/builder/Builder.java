package dev.httpmarco.evelon.common.builder;

import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Builder<B extends Builder<B, A>, A> {

    @Nullable B parent();

    List<B> children();

    void linkPrimaries(PrimaryRepositoryFieldImpl... fields);

    B subBuilder(String subId);

    void push(A arg);

}
