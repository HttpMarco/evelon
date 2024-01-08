package dev.httpmarco.evelon.common.repository.local;

import java.util.concurrent.TimeUnit;

public record LocalStorageEntry<T>(long creationTime, T value) {

    public boolean isExpired(TimeUnit type, long time) {
        return creationTime + type.toMillis(time) >= System.currentTimeMillis();
    }

    public static <T> LocalStorageEntry<T> of(T value) {
        return new LocalStorageEntry<>(System.currentTimeMillis(), value);
    }
}
