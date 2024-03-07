package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.elements.AbstractSimpleParameterStage;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;
import dev.httpmarco.evelon.sql.parent.SqlType;
import dev.httpmarco.evelon.sql.parent.exception.UnknownSqlTypeException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public final class SqlParentParameterStage extends AbstractSimpleParameterStage<SqlQueryBuilder> {

    @Contract(pure = true)
    @Override
    public @Nullable Object serializeElement(Object element) {
        //todo
        return null;
    }
}
