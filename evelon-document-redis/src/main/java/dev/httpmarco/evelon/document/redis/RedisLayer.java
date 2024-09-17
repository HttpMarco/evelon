package dev.httpmarco.evelon.document.redis;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.document.redis.connection.RedisConnection;
import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.query.QueryMethod;

public class RedisLayer extends ConnectableLayer<RedisConnection, RedisProcessReference> {

    public RedisLayer() {
        super(new RedisAuthentication("localhost", 6379, "", false), new RedisFilterHandler());
    }

    @Override
    public void prepped(Repository<?> repository) {

    }

    @Override
    public RedisConnection connection() {
        return null;
    }

    @Override
    protected ProcessRunner<RedisProcessReference> generateRunner() {
        return null;
    }

    @Override
    public <T> QueryMethod<T> queryMethod(Repository<?> repository) {
        return null;
    }
}
