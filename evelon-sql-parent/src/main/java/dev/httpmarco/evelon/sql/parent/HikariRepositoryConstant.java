package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.RepositoryConstant;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class HikariRepositoryConstant {

    public static final RepositoryConstant<SqlType> SQL_TYPE = new RepositoryConstant<>("SQL_TYPE");

}
