package dev.httpmarco.evelon.query.layer;

import dev.httpmarco.evelon.query.Filter;

public interface LayerFilter<T> extends LayerQuery<T>, Filter<T, LayerFilter<T>> {

}
