package dev.httpmarco.evelon.common.builder;

public interface Builder<T extends Builder<?, A>, A> {

    T subBuilder(String subId);

    void push(A arg);

}
