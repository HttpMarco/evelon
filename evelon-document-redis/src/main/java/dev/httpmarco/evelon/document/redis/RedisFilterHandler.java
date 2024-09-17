package dev.httpmarco.evelon.document.redis;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.filtering.FilterHandler;

public class RedisFilterHandler implements FilterHandler<Object, Object> {
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

    @Override
    public Filter<Object, Object> matchIgnoreCase(String id, String value) {
        return null;
    }

    @Override
    public Filter<Object, Object> between(String id, Number min, Number max) {
        return null;
    }

    @Override
    public Filter<Object, Object> min(String id, Number min) {
        return null;
    }

    @Override
    public Filter<Object, Object> max(String id, Number max) {
        return null;
    }
}
