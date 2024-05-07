package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.constant.Constant;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class QueryConstant<T> extends Constant<T> {

    public QueryConstant(String id) {
        super(id);
    }

    public static final QueryConstant<Integer> LIMIT = constant("LIMIT");

    public static final QueryConstant<String> ORDERING = constant("ORDERING");

    public static final QueryConstant<Ordering> ORDERING_TYPE = constant("ORDERING_TYPE");

    @Contract("_ -> new")
    private static <T> @NotNull QueryConstant<T> constant(String id) {
        return new QueryConstant<>(id);
    }
}
