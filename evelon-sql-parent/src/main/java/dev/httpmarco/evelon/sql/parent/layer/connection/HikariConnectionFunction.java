package dev.httpmarco.evelon.sql.parent.layer.connection;

import java.sql.SQLException;

@FunctionalInterface
public interface HikariConnectionFunction<I, O> {

    O apply(I i) throws SQLException;

}
