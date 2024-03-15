package dev.httpmarco.evelon.sql.parent.connection;

import java.sql.SQLException;

@FunctionalInterface
public interface ResultTransformer<D, T> {

    T apply(D i) throws SQLException;

}
