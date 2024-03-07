package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;

public interface RepositoryObjectClass<T> extends RepositoryClass<T> {

    RepositoryField<?>[] fields();

    PrimaryRepositoryFieldImpl<?>[] primaryFields();

}
