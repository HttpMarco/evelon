package dev.httpmarco.evelon.common.local.filtering;

import dev.httpmarco.evelon.common.filtering.common.AbstractTextFilter;
import dev.httpmarco.evelon.common.repository.Repository;

public final class LikeLocalFilter extends AbstractTextFilter<Boolean> {

    public LikeLocalFilter(String id, String value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, String requiredType) {
        return value().contains(requiredType);
    }
}
