package dev.httpmarco.evelon.common.builder;

import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Builder<T extends Builder<?, A>, A> {

    @Nullable T parent();

    List<T> children();

    void linkPrimaries(PrimaryRepositoryFieldImpl... fields);

    T subBuilder(String subId);

    void push(A arg);

}
