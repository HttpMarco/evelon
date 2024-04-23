package dev.httpmarco.evelon;

public interface EntrySerializer<T, C, E extends RepositoryEntry> {

    C serialize(T value, E entry);

    T deserialize(C content, E entry);

}
