package dev.httpmarco.evelon.document.parent;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.document.parent.reference.DocumentProcessReference;
import dev.httpmarco.evelon.filtering.FilterHandler;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.query.QueryMethod;

public abstract class DocumentParentLayer extends Layer<DocumentProcessReference> {

    public DocumentParentLayer(String id, FilterHandler<?, ?> filterHandler) {
        //todo
        super(id, filterHandler);
    }

    @Override
    protected ProcessRunner<DocumentProcessReference> generateRunner() {
        //todo
        return null;
    }

    @Override
    public <T> QueryMethod<T> queryMethod(Repository<?> repository) {
        //todo
        return null;
    }
}
