package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.common.filters.LayerFilterHandler;
import dev.httpmarco.evelon.common.layers.RepositoryLayer;
import dev.httpmarco.evelon.common.model.Model;

public abstract class AbstractSqlLayer extends RepositoryLayer {

    public AbstractSqlLayer(LayerFilterHandler<?, ?> filterHandler, Model model) {
        super(filterHandler, model);
    }





}
