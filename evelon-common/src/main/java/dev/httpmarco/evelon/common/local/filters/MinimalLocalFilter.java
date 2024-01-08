package dev.httpmarco.evelon.common.local.filters;

import dev.httpmarco.evelon.common.filters.impl.AbstractNumberFilter;
import dev.httpmarco.evelon.common.repository.Repository;

public final class MinimalLocalFilter extends AbstractNumberFilter<Boolean> {

    public MinimalLocalFilter(String id, Number value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Number requiredType) {
        return getValue().doubleValue() >= requiredType.doubleValue();
    }
}
