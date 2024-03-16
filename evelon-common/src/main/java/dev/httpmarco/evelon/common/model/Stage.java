package dev.httpmarco.evelon.common.model;

/**
 * @param <T> Class type
 */
public interface Stage<T> {

    boolean isElement(Model model, Class<?> type);

    default boolean isSubStage() {
        return this instanceof SubStage<T>;
    }

    default SubStage<T> asSubStage() {
        return (SubStage<T>) this;
    }

    default boolean isElementStage() {
        return this instanceof ElementStage<T>;
    }

    default ElementStage<T> asElementStage() {
        return (ElementStage<T>) this;
    }
}