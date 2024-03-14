package dev.httpmarco.evelon.common.filtering.impl;

import dev.httpmarco.evelon.common.filtering.common.AbstractSingleObjectFilter;


public abstract class AbstractMatchFilter<T> extends AbstractSingleObjectFilter<T> {

    public AbstractMatchFilter(String id) {
        super(id);
    }
}