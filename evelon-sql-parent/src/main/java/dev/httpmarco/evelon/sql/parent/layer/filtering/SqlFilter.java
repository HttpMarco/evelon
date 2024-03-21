package dev.httpmarco.evelon.sql.parent.layer.filtering;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.repository.Repository;

public abstract class SqlFilter<R> extends Filter<String, R> {

    public SqlFilter(String id, R value) {
        super(id, value);
    }

    static class Match extends SqlFilter<Object> {

        public Match(String id, Object value) {
            super(id, value);
        }

        @Override
        public String filter(Repository<?> repository, Object requiredType) {
            return id() + "=" + value();
        }
    }
}
