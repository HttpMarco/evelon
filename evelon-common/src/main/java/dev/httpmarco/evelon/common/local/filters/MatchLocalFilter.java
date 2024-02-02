package dev.httpmarco.evelon.common.local.filters;

import dev.httpmarco.evelon.common.filters.impl.AbstractSingleObjectFilter;
import dev.httpmarco.evelon.common.repository.Repository;

public class MatchLocalFilter extends AbstractSingleObjectFilter<Boolean> {

    public MatchLocalFilter(String id, Object value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Object requiredType) {
        return repository.equals(value());
    }
}
