package net.bytemc.evelon.local.filters;

import net.bytemc.evelon.filters.AbstractTextFilter;
import net.bytemc.evelon.repository.Repository;

public final class LikeLocalFilter extends AbstractTextFilter<Boolean> {

    public LikeLocalFilter(String id, String value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, String requiredType) {
        return getValue().contains(requiredType);
    }
}
