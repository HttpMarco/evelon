package dev.httpmarco.evelon.document.redis.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.document.redis.RedisFilter;
import dev.httpmarco.evelon.document.redis.RedisProcessReference;
import dev.httpmarco.evelon.process.kind.QueryProcess;

public final class RedisSearchProcess extends QueryProcess<Boolean, RedisProcessReference, RedisFilter<Object>> {

    @Override
    public Boolean run(RepositoryExternalEntry entry, RedisProcessReference reference) {
        var content = reference.connection().connection().sync().get(entry.id());


        return null;
    }
}
