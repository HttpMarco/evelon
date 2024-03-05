package dev.httpmarco.evelon.common.builder;

import java.util.List;

public interface Builder<T extends Builder<?, A>, A> {

    List<T> children();

    T subBuilder(String subId);

    void push(A arg);

}
