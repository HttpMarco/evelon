package dev.httpmarco.evelon.document.parent;

import dev.httpmarco.evelon.filtering.Filter;

public abstract class DocumentFilter<R> extends Filter<R, DocumentFilter<R>> {

    public DocumentFilter(String id, DocumentFilter<R> value) {
        super(id, value);
    }
}
