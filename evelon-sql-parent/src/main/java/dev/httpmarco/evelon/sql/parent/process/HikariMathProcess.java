package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
public final class HikariMathProcess extends QueryProcess<HikariProcessReference, HikariFilter<Object>> {

    private static final String MATH_QUERY = "SELECT %s AS data FROM %s;";
    private String type;

    @Override
    public Object run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        var data = new AtomicReference<>();

        reference.append(HikariFilterUtil.appendFiltering(MATH_QUERY.formatted(type, entry.id()), filters()) + ";", filters().stream().map(Filter::value).toArray(), resultSet -> {
            data.set(resultSet.getObject("data"));
        });

        return data;
    }
}
