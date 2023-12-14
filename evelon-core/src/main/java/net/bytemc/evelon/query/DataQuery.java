package net.bytemc.evelon.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytemc.evelon.filters.Filter;
import net.bytemc.evelon.repository.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public final class DataQuery<T> {

    @Getter
    private final Repository<T> repository;
    private final List<Filter<?, ?>> filters = new ArrayList<>();

}
