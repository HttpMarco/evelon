package dev.httpmarco.evelon.json.redis;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.filtering.FilterHandler;

public final class RedisFilterHandler implements FilterHandler<Object, Object> {

    @Override
    public Filter<Object, Object> match(String id, Object value) {
        return null;
    }

    @Override
    public Filter<Object, Object> noneMatch(String id, Object value) {
        return null;
    }

    @Override
    public Filter<Object, Object> like(String id, String value) {
        return null;
    }
}
