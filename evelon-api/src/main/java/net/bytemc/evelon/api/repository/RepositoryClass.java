package net.bytemc.evelon.api.repository;

public interface RepositoryClass {

    Class<?> clazz();

    RepositoryField[] fields();

    RepositoryField[] primaryFields();

}
