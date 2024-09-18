package dev.httpmarco.evelon.document.redis.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.document.redis.RedisFilter;
import dev.httpmarco.evelon.document.redis.RedisProcessReference;
import dev.httpmarco.evelon.process.kind.QueryProcess;

import java.util.List;

public final class RedisMathProcess extends QueryProcess<Object, RedisProcessReference, RedisFilter<Object>>  {
    @Override
    public Object run(RepositoryExternalEntry entry, RedisProcessReference reference) {




        return List.of();
    }
}
