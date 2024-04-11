package dev.httpmarco.evelon.repository;

import java.lang.reflect.Field;

interface EntryGeneration {

    RepositoryEntry generate(String id, Class<?> clazz, RepositoryEntryType type, Field field);

}
