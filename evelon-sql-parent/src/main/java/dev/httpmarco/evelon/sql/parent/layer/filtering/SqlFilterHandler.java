package dev.httpmarco.evelon.sql.parent.layer.filtering;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.filtering.LayerFilterHandler;

public class SqlFilterHandler implements LayerFilterHandler<String, Object> {

    @Override
    public Filter<String, Object> match(String id, Object value) {
        return new SqlFilter.Match(id, value);
    }

    @Override
    public Filter<String, Object> noneMatch(String id, Object value) {
        return new SqlFilter.NoneMatch(id, value);
    }

    @Override
    public Filter<String, Object> like(String id, String value) {
        return new SqlFilter.Like(id, value);
    }
}
