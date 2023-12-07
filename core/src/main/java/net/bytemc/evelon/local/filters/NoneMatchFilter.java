package net.bytemc.evelon.local.filters;

import net.bytemc.evelon.filters.AbstractSingleObjectFilter;
import net.bytemc.evelon.filters.Filter;
import net.bytemc.evelon.repository.Repository;

public final class NoneMatchFilter extends MatchLocalFilter {

    public NoneMatchFilter(String id, Object value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Object requiredType) {
        return !super.filter(repository, requiredType);
    }
}
