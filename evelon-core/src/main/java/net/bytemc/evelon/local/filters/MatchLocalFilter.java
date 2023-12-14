package net.bytemc.evelon.local.filters;

import net.bytemc.evelon.filters.AbstractSingleObjectFilter;
import net.bytemc.evelon.repository.Repository;

public class MatchLocalFilter extends AbstractSingleObjectFilter<Boolean> {

    public MatchLocalFilter(String id, Object value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Object requiredType) {
        return repository.equals(getValue());
    }
}
