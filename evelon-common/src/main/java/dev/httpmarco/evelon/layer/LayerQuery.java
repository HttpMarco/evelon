package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.query.Query;

import java.util.List;

public interface LayerQuery<T> extends Query<T> {

    List<T> find();

}
