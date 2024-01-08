package net.bytemc.evelon.common.repository;

public interface RepositoryClass {

    Class<?> clazz();

    RepositoryField[] fields();

    RepositoryField[] primaryFields();

}
