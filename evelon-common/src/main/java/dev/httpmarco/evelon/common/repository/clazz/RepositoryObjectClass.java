package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;

public interface RepositoryObjectClass<C> extends RepositoryClass<C> {

    RepositoryField[] fields();

    PrimaryRepositoryFieldImpl[] primaryFields();

}
