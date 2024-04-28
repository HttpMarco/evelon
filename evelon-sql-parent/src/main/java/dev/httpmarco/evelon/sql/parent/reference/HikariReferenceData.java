package dev.httpmarco.evelon.sql.parent.reference;

import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionFunction;

public record HikariReferenceData<R>(String query, Object[] values, HikariConnectionFunction<R> consumer) {

}
