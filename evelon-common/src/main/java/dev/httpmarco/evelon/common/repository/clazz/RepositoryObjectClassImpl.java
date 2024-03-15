package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
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
    private final Set<RepositoryField<?>> primaryFields = new HashSet<>();

    public RepositoryObjectClassImpl(Repository<?> repository, Class<T> clazz) {
        super(clazz);

        this.fields = Arrays.stream(clazz.getDeclaredFields()).map(field -> new RepositoryFieldImpl<>(repository, field, this)).toArray(RepositoryField[]::new);
    }
}
