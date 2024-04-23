package dev.httpmarco.evelon.exception;

public final class RepositoryTypeNotAllowedException extends RuntimeException {

    public RepositoryTypeNotAllowedException() {
        super("Repositories can be only synthetic objects.");
    }
}
