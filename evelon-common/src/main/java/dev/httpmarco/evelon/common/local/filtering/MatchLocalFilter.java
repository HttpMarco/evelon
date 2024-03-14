package dev.httpmarco.evelon.common.local.filtering;

import dev.httpmarco.evelon.common.filtering.impl.AbstractMatchFilter;
import dev.httpmarco.evelon.common.repository.Repository;

public class MatchLocalFilter extends AbstractMatchFilter<Boolean> {

    public MatchLocalFilter(String id, Object value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Object requiredType) {
        return repository.equals(value());
    }
}
