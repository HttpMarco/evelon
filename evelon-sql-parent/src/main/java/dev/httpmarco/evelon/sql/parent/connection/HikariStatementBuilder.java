package dev.httpmarco.evelon.sql.parent.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface HikariStatementBuilder<I> {

    I apply(PreparedStatement data) throws SQLException;

}
