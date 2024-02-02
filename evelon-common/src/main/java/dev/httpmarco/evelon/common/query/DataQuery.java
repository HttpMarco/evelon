package dev.httpmarco.evelon.common.query;

import dev.httpmarco.evelon.common.repository.Repository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import dev.httpmarco.evelon.common.filters.Filter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Accessors(fluent = true)
public class DataQuery<T> implements Query {

    @Getter
    private final Repository<T> repository;
    private final List<Filter<?, ?>> filters = new ArrayList<>();

}
