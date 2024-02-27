package dev.httpmarco.evelon.common.repository;

public interface InitializeRepository {

    /**
     * Initialize the repository for the layer.
     */
    <T> void initializeRepository(Repository<T> repository);

}
