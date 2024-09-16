package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public final class HikariMathProcess<T> extends QueryProcess<Object, HikariProcessReference, HikariFilter<Object>> {

    private static final String MATH_QUERY = "SELECT %s AS data FROM %s";
    private String type;
    private T defaultValue;

    @Override
    public @NotNull Object run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        return reference.directly(HikariFilterUtil.appendFiltering(MATH_QUERY.formatted(type, entry.id()), filters()) + ";", filterArguments(), result -> result.getObject("data") != null ? result.getObject("data") : defaultValue);
    }
}
