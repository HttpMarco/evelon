package dev.httpmarco.evelon.sql.parent.model;

import dev.httpmarco.evelon.common.model.elements.AbstractSimpleParameterStage;
import dev.httpmarco.evelon.sql.parent.sql.SqlQueryBuilder;

public final class SqlParentParameterStage extends AbstractSimpleParameterStage<SqlQueryBuilder> {

    @Override
    public Object serializeElement(Object element) {
        return null;
    }
}
