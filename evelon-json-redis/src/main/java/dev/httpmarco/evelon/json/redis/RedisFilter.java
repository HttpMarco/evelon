package dev.httpmarco.evelon.json.redis;

import dev.httpmarco.evelon.filtering.Filter;

public class RedisFilter extends Filter<String, Object> {

    public RedisFilter(String id, Object value) {
        super(id, value);
    }

    @Override
    public String filter() {
        return "";
    }
}
