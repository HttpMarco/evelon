package dev.httpmarco.evelon.sql.parent.filtering;

import dev.httpmarco.evelon.common.filtering.impl.AbstractMatchFilter;
import dev.httpmarco.evelon.common.repository.Repository;
import org.jetbrains.annotations.NotNull;

public class SqlMatchFilter extends AbstractMatchFilter<String> {

    public SqlMatchFilter(String id) {
        super(id);
    }

    @Override
    public String filter(Repository<?> repository, @NotNull Object requiredType) {
        return id() + "=" + requiredType.toString();
    }
}
