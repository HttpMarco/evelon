package dev.httpmarco.evelon.common.builder;

import java.sql.SQLException;

@FunctionalInterface
public interface BuilderTransformer<D, T> {

    T apply(D i) throws SQLException;

}
