package net.bytemc.evelon.local;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record LocalStorageEntry<T>(long creationTime, T value) {

    @Contract("_ -> new")
    public static <T> @NotNull LocalStorageEntry<T> of(T value) {
        return new LocalStorageEntry<T>(System.currentTimeMillis(), value);
    }
}
