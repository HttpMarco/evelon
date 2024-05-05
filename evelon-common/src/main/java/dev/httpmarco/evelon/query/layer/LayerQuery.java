package dev.httpmarco.evelon.query.layer;

import dev.httpmarco.evelon.query.QueryMethod;

public interface LayerQuery<T> extends QueryMethod<T> {

    LayerQueryFilter<T> filter();

}
