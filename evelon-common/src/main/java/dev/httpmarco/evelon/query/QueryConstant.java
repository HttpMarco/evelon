package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryEntry;
import dev.httpmarco.evelon.constant.Constant;
import dev.httpmarco.osgan.reflections.Reflections;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class QueryConstant<T> extends Constant<T> {

    public QueryConstant(String id) {
        super(id);
    }

    public static final QueryConstant<Integer> LIMIT = constant("LIMIT");

    public static final QueryConstant<String> ORDERING = constant("ORDERING");

    public static final QueryConstant<Ordering> ORDERING_TYPE = constant("ORDERING_TYPE");

    public static final QueryConstant<PrimaryShortCut> PRIMARY_SHORTCUT = constant("SHORT_CUT");

    @Contract("_ -> new")
    private static <T> @NotNull QueryConstant<T> constant(String id) {
        return new QueryConstant<>(id);
    }

    public static final class PrimaryShortCut {

        private final Map<String, Object> primaries = new LinkedHashMap<>();

        public static PrimaryShortCut append(@NotNull List<RepositoryEntry> primaries, Object parent) {
            var shortCut = new PrimaryShortCut();

            System.err.println("primary short cut" + primaries.size());
            for (var primary : primaries) {
                var primaryValue = Reflections.on(primary.constants().constant(RepositoryConstant.PARAM_FIELD)).value(parent);
                shortCut.primaries.put(primary.id(), primaryValue);
            }

            return shortCut;
        }

        public Object value(String id) {
            return primaries.get(id);
        }

        public Object value(RepositoryEntry entry) {
            return primaries.get(entry.id());
        }
    }
}
