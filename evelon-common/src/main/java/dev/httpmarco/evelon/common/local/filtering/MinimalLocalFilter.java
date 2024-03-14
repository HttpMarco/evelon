package dev.httpmarco.evelon.common.local.filtering;

import dev.httpmarco.evelon.common.filtering.common.AbstractNumberFilter;
import dev.httpmarco.evelon.common.repository.Repository;

public final class MinimalLocalFilter extends AbstractNumberFilter<Boolean> {

    public MinimalLocalFilter(String id, Number value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Number requiredType) {
        return value().doubleValue() >= requiredType.doubleValue();
    }
}
