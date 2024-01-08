package dev.httpmarco.evelon.common.local.filters;

import dev.httpmarco.evelon.common.filters.impl.AbstractTextFilter;
import dev.httpmarco.evelon.common.repository.Repository;

public final class LikeLocalFilter extends AbstractTextFilter<Boolean> {

    public LikeLocalFilter(String id, String value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, String requiredType) {
        return getValue().contains(requiredType);
    }
}
