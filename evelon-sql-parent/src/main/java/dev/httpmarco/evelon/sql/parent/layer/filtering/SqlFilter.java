package dev.httpmarco.evelon.sql.parent.layer.filtering;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.repository.Repository;

import java.util.List;

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
            if (requiredType instanceof String) {
                return id() + " = '" + value() + "'";
            } else {
                return id() + " = " + value();
            }
        }
    }

    static class NoneMatch extends SqlFilter<Object> {

        public NoneMatch(String id, Object value) {
            super(id, value);
        }

        @Override
        public String filter(Repository<?> repository, Object requiredType) {
            //todo
            if (value() instanceof String) {
                return id() + " != '" + value() + "'";
            } else {
                return id() + " != " + value();
            }
        }
    }

    static class Like extends SqlFilter<Object> {

        public Like(String id, Object value) {
            super(id, value);
        }

        @Override
        public String filter(Repository<?> repository, Object requiredType) {
            return id() + " LIKE '%" + value() + "%'";
        }
    }

    // todo move to filter handler
    public static String convertFilters(Repository<?> repository, List<Filter<?, ?>> filters) {
        if (filters.isEmpty()) {
            return "";
        }
        return " WHERE " + String.join(" AND ", filters.stream().map(it -> it.filter(repository, null).toString()).toList());
    }
}