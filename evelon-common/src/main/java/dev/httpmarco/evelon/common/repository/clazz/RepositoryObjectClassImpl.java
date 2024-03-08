package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import dev.httpmarco.evelon.common.repository.field.RepositoryFieldImpl;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Accessors(fluent = true)
public class RepositoryObjectClassImpl<T> extends RepositoryClassImpl<T> implements RepositoryObjectClass<T> {

    private final RepositoryField<?>[] fields;
    private final Set<PrimaryRepositoryFieldImpl<?>> primaryFields = new HashSet<>();

    public RepositoryObjectClassImpl(Repository<?> repository, Class<T> clazz) {
        super(clazz);

        this.fields = Arrays.stream(clazz.getDeclaredFields())
                .map(field -> {

                    RepositoryField<?> repositoryField;

                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        var primaryRepositoryField = new PrimaryRepositoryFieldImpl<>(repository, field, this);
                        primaryFields.add(primaryRepositoryField);
                        repositoryField = primaryRepositoryField;
                    } else {
                        // todo check is object or not
                        repositoryField = new RepositoryFieldImpl<>(repository, field, this);
                    }

                    return repositoryField;
                }).toArray(RepositoryField[]::new);
    }
}
