package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.sql.parent.types.Type;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class HikariRepositoryConstant {

    public static final RepositoryConstant<Type> SQL_TYPE = new RepositoryConstant<>("SQL_TYPE");

}
