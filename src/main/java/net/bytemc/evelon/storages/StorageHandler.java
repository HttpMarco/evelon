package net.bytemc.evelon.storages;

import lombok.Getter;
import net.bytemc.evelon.local.LocalStorage;
import net.bytemc.evelon.sql.DatabaseStorage;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class StorageHandler {

    @Getter
    private static final List<Storage> storages = new ArrayList<>();

    static {
        //disable logging
        System.setProperty("mariadb.logging.disable", "false");

        storages.add(new DatabaseStorage());
        storages.add(new LocalStorage());
    }

    /**
     * @param clazz the class of the storage
     * @param <T>
     * @return the current storage of the declared class, if not found, it will return null
     */
    public static <T extends Storage> @Nullable T getStorage(Class<T> clazz) {
        return (T) storages.stream().filter(it -> it.getClass().equals(clazz)).findFirst().get();
    }
}
