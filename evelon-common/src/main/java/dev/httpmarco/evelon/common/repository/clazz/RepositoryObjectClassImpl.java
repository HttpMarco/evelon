package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import dev.httpmarco.evelon.common.repository.field.RepositoryFieldImpl;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Arrays;

@Getter
@Accessors(fluent = true)
public class RepositoryObjectClassImpl<C> extends RepositoryClassImpl<C> implements RepositoryObjectClass<C> {

    private final RepositoryField[] fields;
    private final PrimaryRepositoryFieldImpl[] primaryFields;

    public RepositoryObjectClassImpl(Class<C> clazz) {
        super(clazz);

        this.fields = Arrays.stream(clazz.getDeclaredFields())
                .map(field -> field.isAnnotationPresent(PrimaryKey.class) ? new PrimaryRepositoryFieldImpl(field, this) : new RepositoryFieldImpl(field, this))
                .toArray(RepositoryField[]::new);

        this.primaryFields = Arrays.stream(fields)
                .filter(repositoryField -> repositoryField instanceof PrimaryRepositoryFieldImpl)
                .toArray(PrimaryRepositoryFieldImpl[]::new);
    }


    @Override
    public Class<C> clazz() {
        return super.clazz();
    }
}
