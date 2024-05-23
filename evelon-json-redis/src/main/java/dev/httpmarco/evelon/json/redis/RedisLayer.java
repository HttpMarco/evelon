package dev.httpmarco.evelon.json.redis;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.json.redis.query.RedisLayerQuery;
import dev.httpmarco.evelon.layer.connection.ConnectableLayer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.query.QueryMethod;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class RedisLayer extends ConnectableLayer<RedisConnection, RedisProcessReference> {

    private final RedisConnection connection = new RedisConnection();


    public RedisLayer() {
        super(new RedisAuthentication(), new RedisFilterHandler());
    }

    @Override
    public void prepped(Repository<?> repository) {

    }

    @Override
    protected ProcessRunner<RedisProcessReference> generateRunner() {
        return new RedisConnectionRunner();
    }

    @Override
    public <T> QueryMethod<T> queryMethod(Repository<?> repository) {
        return new RedisLayerQuery<>();
    }
}
