package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.stage.Stage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter @Accessors(fluent = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class RepositoryClass<T> {

    // subordinate repository
    private Repository<?> repository;
    // original class of the repository class
    private Class<T> originalClass;
    // detected stage of the class
    private Stage.Type type;

}