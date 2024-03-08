package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;

import java.util.Set;

public interface RepositoryObjectClass<T> extends RepositoryClass<T> {

    RepositoryField<?>[] fields();

    Set<PrimaryRepositoryFieldImpl<?>> primaryFields();

}
