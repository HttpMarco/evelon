package dev.httpmarco.evelon.common.filters.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.httpmarco.evelon.common.filters.Filter;

import java.util.Date;

@Getter
@AllArgsConstructor
public abstract class AbstractDateFilter<T> implements Filter<T, Date> {

    private final String id;
    private final Date value;

}