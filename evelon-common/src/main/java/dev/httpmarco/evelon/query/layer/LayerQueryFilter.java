package dev.httpmarco.evelon.query.layer;

import dev.httpmarco.evelon.query.QueryFilter;

public interface LayerQueryFilter<T> extends LayerQuery<T>, QueryFilter<T, LayerQueryFilter<T>> {

}
