package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.filtering.FilterHandler;

public final class HikariFilterHandler implements FilterHandler<String, Object> {

    @Override
    public Filter<String, Object> match(String id, Object value) {
        return new HikariFilter.SequenceMatchFilter(id, value, "=");
    }

    @Override
    public Filter<String, Object> noneMatch(String id, Object value) {
        return new HikariFilter.SequenceMatchFilter(id, value, "!=");
    }

    @Override
    public Filter<String, Object> like(String id, String value) {
        return new HikariFilter.Like(id, value);
    }
}
