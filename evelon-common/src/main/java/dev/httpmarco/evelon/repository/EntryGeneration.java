package dev.httpmarco.evelon.repository;

import java.lang.reflect.Field;

public interface EntryGeneration {

    RepositoryEntry generate(String id, Class<?> clazz, Field field);

}
