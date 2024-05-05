package dev.httpmarco.evelon.query.layer;

import dev.httpmarco.evelon.query.QueryMethod;

import java.util.List;

public interface LayerQuery<T> extends QueryMethod<T> {

    LayerFilter<T> filter();

}
