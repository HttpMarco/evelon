package dev.httpmarco.evelon.query;

import java.util.List;

public interface LayerQuery<T> extends Query<T> {

    List<T> find();

    long count();

}
