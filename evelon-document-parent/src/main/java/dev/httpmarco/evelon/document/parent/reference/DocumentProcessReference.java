package dev.httpmarco.evelon.document.parent.reference;

import dev.httpmarco.evelon.document.parent.connection.DocumentConnection;
import dev.httpmarco.evelon.process.ProcessReference;

public final class DocumentProcessReference extends ProcessReference<DocumentConnection> {

    public DocumentProcessReference(DocumentConnection connection) {
        super(connection);
    }
}
