package net.bytemc.evelon.sql;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Map;

public interface ElementStage<T> extends Stage<T> {

    /**
     * @param field is the field of repository property
     * @param repository the class which need rows in a database, can be a repository or a sub entity
     * @return a map with all columns and there sql type
     */
    Pair<Field, String> elementRowData(@Nullable Field field, RepositoryClass<T> repository);

    /**
     * @param field is the field of repository property
     * @param repository an anonymous class which not know the type
     * @return a map with all columns and there sql type
     */
    default Pair<Field, String> anonymousElementRowData(@Nullable Field field, RepositoryClass<?> repository){
        return elementRowData(field, (RepositoryClass<T>) repository);
    }

    /**
     * @param repositoryClass is the repository class of the object
     * @param field is the field of repository property
     * @param object the class which type is the stage element type
     * @return a map with the name of database row and the value of the property
     */
    Map<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, T object);

    /**
     * @param repositoryClass is the repository class of the object
     * @param field is the field of repository property
     * @param object the class which type is the stage element type
     * @return a map with the name of database row and the value of the property
     */
    default Map<String, String> anonymousElementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Object object) {
        return elementEntryData(repositoryClass, field, (T) object);
    }

    /**
     * @param clazz is the repository class of the object
     * @param <T> the type of the object
     * @return a new object of the type
     */
    <T> T createObject(RepositoryClass<T> clazz, String id, DatabaseResultSet.Table table);

}
