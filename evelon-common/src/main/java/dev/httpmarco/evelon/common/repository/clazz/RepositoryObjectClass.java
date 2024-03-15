package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.repository.RepositoryField;

import java.util.Set;

public interface RepositoryObjectClass<T> extends RepositoryClass<T> {

    RepositoryField<?>[] fields();

    Set<RepositoryField<?>> primaryFields();

}
