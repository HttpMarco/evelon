package net.bytemc.evelon.model.relational;

public interface Stage<T, I> {

    T serialize(String id, I input);

    boolean isElement(Class<?> type);

}