package net.bytemc.evelon.repository;

import net.bytemc.evelon.local.LocalStorage;
import net.bytemc.evelon.repository.annotations.Entity;
import net.bytemc.evelon.sql.DatabaseHelper;
import net.bytemc.evelon.sql.analyze.TableAnalyseProcess;
import net.bytemc.evelon.sql.process.TableCreationProcess;
import net.bytemc.evelon.storages.StorageHandler;
import org.jetbrains.annotations.NotNull;

public record Repository<T>(RepositoryClass<T> repositoryClass) {

    /**
     * @param clazz the class of the repository
     * @param <T> the type of the repository
     * @return a new instance of {@link Repository}
     * Check the state of the current repository in the database and create a new table if it does not exist.
     */
    public static <T> @NotNull Repository<T> create(Class<T> clazz) {
        // create a local list
        var repository = new Repository<>(new RepositoryClass<>(clazz));

        StorageHandler.getStorage(LocalStorage.class).initializeRepository(repository);

        // check if table exists in sql, else create table
        // check also old table names, because the table can be renamed and not lose the data.
        if (!DatabaseHelper.isTableExists(repository.getName()) && !DatabaseHelper.isTableExists(repository.repositoryClass.clazz().getSimpleName().toLowerCase())) {
            TableCreationProcess.createTable(repository);
        } else {
            // analyze the current table and check if the table has all columns and more
            TableAnalyseProcess.run(repository);
        }
        return repository;
    }

    /**
     * @return the name of the repository.
     * If the repository class is annotated with {@link Entity}, the name will be the value of the annotation.
     * Else the name will be the simple name of the repository class.
     */
    public String getName() {
        if (repositoryClass.clazz().isAnnotationPresent(Entity.class)) {
            var data = repositoryClass.clazz().getDeclaredAnnotation(Entity.class);
            if (data.name().length() == 0) {
                return this.repositoryClass.clazz().getSimpleName();
            } else {
                return data.name();
            }
        } else {
            return this.repositoryClass.clazz().getSimpleName();
        }
    }

    /**
     * @param name the name of the child
     * @return the name of the child with the name of the repository
     */
    public String appendChildrenName(String name) {
        // static separator for the table name '_'
        return getName() + "_" + name;
    }

    /**
     * @return a new instance of {@link RepositoryQuery}
     * Use this method to apply action on any storage
     */
    public RepositoryQuery<T> query() {
        return new RepositoryQuery<>(this);
    }
}