package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.repository.RepositoryField;

public interface RepositoryObjectClass<C> extends RepositoryClass<C> {

    RepositoryField[] fields();

    RepositoryField[] primaryFields();

}
