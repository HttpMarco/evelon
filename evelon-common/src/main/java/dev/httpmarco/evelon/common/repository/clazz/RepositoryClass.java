package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;

public interface RepositoryClass<T> {

    /**
     * get the java original class of the repository class
     * @return java class
     */
    Class<T> clazz();

    /**
     * get cached repository class stage
     * @param model of layer
     * @return stage of model
     * @param <B> layer builder
     */
    <B extends Builder<B, ?>> Stage<T, B> stageOf(Model<B> model);

    default RepositoryObjectClass<T> asObjectClass() {
        if (this instanceof RepositoryObjectClass<T> objectClass) {
            return objectClass;
        }
        throw new RuntimeException("RepositoryClass is not an object class: " + clazz().getSimpleName());
    }
}
