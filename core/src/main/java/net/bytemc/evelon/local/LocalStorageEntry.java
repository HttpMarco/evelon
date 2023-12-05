package net.bytemc.evelon.local;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public record LocalStorageEntry<T>(long creationTime, T value) {

    public boolean isExpired(TimeUnit type, long time) {
        return creationTime + type.toMillis(time) >= System.currentTimeMillis();
    }

    @Contract("_ -> new")
    public static <T> @NotNull LocalStorageEntry<T> of(T value) {
        return new LocalStorageEntry<>(System.currentTimeMillis(), value);
    }
}
