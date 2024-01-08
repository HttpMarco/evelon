package dev.httpmarco.evelon.common.local.filters;

import dev.httpmarco.evelon.common.repository.Repository;

public final class NoneMatchFilter extends MatchLocalFilter {

    public NoneMatchFilter(String id, Object value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Object requiredType) {
        return !super.filter(repository, requiredType);
    }
}
