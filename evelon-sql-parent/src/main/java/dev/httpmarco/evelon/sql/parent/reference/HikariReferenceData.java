package dev.httpmarco.evelon.sql.parent.reference;

import java.util.function.Consumer;

public record HikariReferenceData<R>(String query, Object[] values, Consumer<R> consumer) {

}
