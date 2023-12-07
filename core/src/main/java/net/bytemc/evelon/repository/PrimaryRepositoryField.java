package net.bytemc.evelon.repository;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class PrimaryRepositoryField extends RepositoryField{

    public PrimaryRepositoryField(@NotNull Field field) {
        super(field);
    }
}
