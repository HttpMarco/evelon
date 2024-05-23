package dev.httpmarco.evelon.json.redis;

import dev.httpmarco.evelon.process.ProcessRunner;

public final class RedisConnectionRunner extends ProcessRunner<RedisProcessReference> {

    @Override
    public RedisProcessReference generateBase() {
        return null;
    }

    @Override
    protected void query(RedisProcessReference query) {

    }

    @Override
    protected void update(RedisProcessReference query) {

    }
}
