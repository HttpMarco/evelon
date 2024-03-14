package dev.httpmarco.evelon.common.local.filtering;

import dev.httpmarco.evelon.common.filtering.Filter;
import dev.httpmarco.evelon.common.repository.Repository;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class MatchLocalFilter extends Filter<Boolean, Object> {

    public MatchLocalFilter(String id, Object value) {
        super(id, value);
    }

    @Override
    public Boolean filter(Repository<?> repository, Object requiredType) {
        return repository.equals(value());
    }
}
