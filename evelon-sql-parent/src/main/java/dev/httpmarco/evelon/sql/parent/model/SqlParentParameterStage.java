package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.elements.AbstractSimpleParameterStage;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;
import dev.httpmarco.evelon.sql.parent.SqlType;
import dev.httpmarco.evelon.sql.parent.exception.UnknownSqlTypeException;

public final class SqlParentParameterStage extends AbstractSimpleParameterStage<SqlQueryBuilder, SqlType> {

    @Override
    public Object serializeElement(Object element) {
        //todo
        return null;
    }

    @Override
    public SqlType classBuilderType(Class<?> element) {
        var type = SqlType.find(element);
        if (type == SqlType.UNKNOWN) {
            throw new UnknownSqlTypeException(element);
        }
        return type;
    }
}
