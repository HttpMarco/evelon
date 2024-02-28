package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.elements.AbstractSimpleParameterStage;
import dev.httpmarco.evelon.sql.parent.SqlQueryBuilder;
import dev.httpmarco.evelon.sql.parent.SqlType;

public final class SqlParentParameterStage extends AbstractSimpleParameterStage<SqlQueryBuilder, SqlType> {

    @Override
    public Object serializeElement(Object element) {
        //todo
        return null;
    }

    @Override
    public SqlType classBuilderType(Object element) {
        //todo
        return SqlType.BIT;
    }
}
