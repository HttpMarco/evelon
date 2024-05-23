package dev.httpmarco.evelon.json.redis.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.json.redis.RedisFilter;
import dev.httpmarco.evelon.json.redis.RedisProcessReference;
import dev.httpmarco.evelon.process.kind.UpdateProcess;

public class RedisCreateProcess extends UpdateProcess<RedisProcessReference, RedisFilter> {

    @Override
    public void run(RepositoryExternalEntry entry, RedisProcessReference reference) {
        System.out.println("testing");
    }
}
