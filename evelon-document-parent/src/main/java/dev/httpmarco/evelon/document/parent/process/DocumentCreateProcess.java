package dev.httpmarco.evelon.document.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.document.parent.DocumentFilter;
import dev.httpmarco.evelon.document.parent.reference.DocumentProcessReference;
import dev.httpmarco.evelon.process.kind.UpdateProcess;

public final class DocumentCreateProcess extends UpdateProcess<DocumentProcessReference, DocumentFilter<Object>> {

    @Override
    public void run(RepositoryExternalEntry entry, DocumentProcessReference reference) {
        //todo generate jsonObject
    }
}
