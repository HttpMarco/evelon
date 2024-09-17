package dev.httpmarco.evelon.document.redis.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.document.redis.RedisFilter;
import dev.httpmarco.evelon.document.redis.RedisProcessReference;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import org.jetbrains.annotations.NotNull;

public final class RedisCountProcess extends QueryProcess<Long, RedisProcessReference, RedisFilter<Object>> {

    @Override
    public Long run(@NotNull RepositoryExternalEntry entry, @NotNull RedisProcessReference reference) {
        var pattern = entry.id() + "*";
        var scanArgs = ScanArgs.Builder.matches(pattern);
        var scanCursor = ScanCursor.INITIAL;
        var keyCount = 0L;

        do {
            var cursor = reference.connection().connection().sync().scan(scanCursor, scanArgs);
            keyCount += cursor.getKeys().size();
            scanCursor = cursor;
        } while (!scanCursor.isFinished());

        return keyCount;
    }
}
