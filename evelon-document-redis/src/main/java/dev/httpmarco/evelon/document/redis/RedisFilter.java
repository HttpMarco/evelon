package dev.httpmarco.evelon.document.redis;

import dev.httpmarco.evelon.filtering.Filter;

public abstract class RedisFilter<R> extends Filter<String, R> {

    public RedisFilter(String id, R value) {
        super(id, value);
    }
}
