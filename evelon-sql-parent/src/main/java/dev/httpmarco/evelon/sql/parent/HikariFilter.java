package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.Repository;
import org.jetbrains.annotations.NotNull;

public abstract class HikariFilter<R> extends Filter<String, R> {

    public HikariFilter(String id, R value) {
        super(id, value);
    }

    public static final class SequenceMatchFilter extends HikariFilter<Object> {

        private final String filterSequence;

        public SequenceMatchFilter(String id, Object value, String filterSequence) {
            super(id, value);
            this.filterSequence = filterSequence;
        }

        @Override
        public @NotNull String filter() {
            return id() + " " + filterSequence + " ?";
        }
    }

    static class Like extends HikariFilter<Object> {

        public Like(String id, Object value) {
            super(id, value);
        }

        @Override
        public String filter() {
            return id() + " LIKE '%?%'";
        }
    }
}
