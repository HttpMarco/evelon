package dev.httpmarco.evelon.sql.parent.connection;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface HikariStatementBuilder<I> {

    I apply(ResultSet data) throws SQLException;

}
