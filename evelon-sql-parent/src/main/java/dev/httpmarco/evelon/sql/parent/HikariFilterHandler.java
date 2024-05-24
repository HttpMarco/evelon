package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.filtering.FilterHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class HikariFilterHandler implements FilterHandler<String, Object> {

    @Contract("_, _ -> new")
    @Override
    public @NotNull Filter<String, Object> match(String id, Object value) {
        return new HikariFilter.SequenceMatchFilter(id, value, "=");
    }

    @Contract("_, _ -> new")
    @Override
    public @NotNull Filter<String, Object> noneMatch(String id, Object value) {
        return new HikariFilter.SequenceMatchFilter(id, value, "!=");
    }

    @Contract("_, _ -> new")
    @Override
    public @NotNull Filter<String, Object> like(String id, String value) {
        return new HikariFilter.Like(id, value);
    }

    @Override
    public Filter<String, Object> matchIgnoreCase(String id, String value) {
        return this.match(id, value);
    }
}
