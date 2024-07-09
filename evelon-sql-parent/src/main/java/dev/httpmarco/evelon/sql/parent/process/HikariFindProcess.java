package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryEntry;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.external.RepositoryCollectionEntry;
import dev.httpmarco.evelon.external.RepositoryMapEntry;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.query.QueryConstant;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.osgan.reflections.Reflections;
import dev.httpmarco.osgan.utils.data.Pair;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class HikariFindProcess extends QueryProcess<HikariProcessReference, HikariFilter<Object>> {

    private static final String SELECT_QUERY = "SELECT %s FROM %s";

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull Object run(@NotNull RepositoryExternalEntry entry, HikariProcessReference reference) {
        return null;
    }

    private Object renderSingleValue(@NotNull RepositoryEntry entry, Object untouchedValue) {
        return null;
    }
}
