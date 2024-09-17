package dev.httpmarco.evelon.document.redis;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.document.redis.connection.RedisConnection;
import dev.httpmarco.evelon.document.redis.query.RedisDocumentQuery;
import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.query.QueryMethod;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class RedisLayer extends ConnectableLayer<RedisConnection, RedisProcessReference> {

    private RedisConnection connection;

    public RedisLayer() {
        super(new RedisAuthentication("localhost", 6379, "", false), new RedisFilterHandler());
    }

    @Override
    public void prepped(Repository<?> repository) {

    }

    @Override
    protected ProcessRunner<RedisProcessReference> generateRunner() {
        return new RedisRunner(this.connection = new RedisConnection());
    }

    @Override
    public <T> QueryMethod<T> queryMethod(Repository<?> repository) {
        return new RedisDocumentQuery<>(this, runner());
    }
}
