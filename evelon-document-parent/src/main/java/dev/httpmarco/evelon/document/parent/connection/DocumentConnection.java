package dev.httpmarco.evelon.document.parent.connection;

import dev.httpmarco.evelon.document.parent.reference.DocumentProcessReference;
import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.layer.connection.ConnectionAuthentication;
import org.jetbrains.annotations.NotNull;

public final class DocumentConnection implements Connection<Object, DocumentProcessReference> {

    @Override
    public void connect(ConnectionAuthentication credentials) {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public Object connection() {
        return null;
    }

    @Override
    public void update(DocumentProcessReference query) {

    }

    @Override
    public void query(@NotNull DocumentProcessReference query) {

    }
}
