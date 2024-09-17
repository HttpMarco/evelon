package dev.httpmarco.evelon.document.redis;

import dev.httpmarco.evelon.document.parent.DocumentParentLayer;
import dev.httpmarco.evelon.filtering.FilterHandler;

public class RedisLayer extends DocumentParentLayer {

    public RedisLayer(String id, FilterHandler<?, ?> filterHandler) {
        super(id, filterHandler);
    }
}
