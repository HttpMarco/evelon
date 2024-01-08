package dev.httpmarco.evelon.common.repository;

public interface RepositoryClass<T> {

    Class<T> clazz();

    RepositoryField[] fields();

    RepositoryField[] primaryFields();

}
