package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.RepositoryConstant;
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
    public @NotNull Object run(@NotNull RepositoryExternalEntry entry, HikariProcessReference reference) {
        var items = new ArrayList<>();
        var searchedItems = new ArrayList<String>();

        for (var child : entry.children()) {
            if (child instanceof RepositoryExternalEntry) {
                continue;
            }
            searchedItems.add(child.id());
        }

        var itemStringList = String.join(", ", searchedItems);
        var query = HikariFilterUtil.appendFiltering(SELECT_QUERY.formatted(itemStringList, entry.id()), filters());

        if (constants().has(QueryConstant.ORDERING)) {
            query = query + " ORDER BY " + constants().constant(QueryConstant.ORDERING);

            if (constants().has(QueryConstant.ORDERING_TYPE)) {
                if (constants().constant(QueryConstant.ORDERING_TYPE) == Ordering.ASCENDING) {
                    query = query + " ASC";
                } else {
                    query = query + " DESC";
                }
            } else {
                query = query + " ASC";
            }
        }

        if (constants().has(QueryConstant.LIMIT)) {
            query = query + " LIMIT " + constants().constant(QueryConstant.LIMIT);
        }


        if (constants().has(QueryConstant.OFFSET)) {
            query = query + " OFFSET " + constants().constant(QueryConstant.OFFSET);
        }

        if (constants().has(QueryConstant.RANDOMIZE) && !(constants().has(QueryConstant.LIMIT) && (constants().constant(QueryConstant.LIMIT) <= 1))) {
            query = query + " ORDER BY RAND();";
        }

        query = query + ";";

        reference.append(query, filters().stream().map(Filter::value).toArray(), resultSet -> {
            try {
                if (entry instanceof RepositoryCollectionEntry collectionEntry && !(collectionEntry.typeEntry() instanceof RepositoryExternalEntry)) {
                    var value = resultSet.getObject(collectionEntry.typeEntry().id());

                    // todo fix: duplicated code
                    if (collectionEntry.typeEntry().constants().has(RepositoryConstant.VALUE_RENDERING)) {
                        value = collectionEntry.typeEntry().constants().constant(RepositoryConstant.VALUE_RENDERING).apply(value);
                    }

                    items.add(value);
                    return;
                }

                if (entry instanceof RepositoryMapEntry mapEntry && !(mapEntry.keyEntry() instanceof RepositoryExternalEntry) && !(mapEntry.valueEntry() instanceof RepositoryExternalEntry)) {
                    items.add(new Pair<>(resultSet.getObject(mapEntry.keyEntry().id()), resultSet.getObject(mapEntry.valueEntry().id())));
                    return;
                }

                var reflections = Reflections.on(entry.clazz());

                // we must use the value type of collection entry to create the object
                if (entry instanceof RepositoryCollectionEntry collectionEntry) {
                    reflections = Reflections.on(collectionEntry.typeEntry().clazz());
                }

                var object = reflections.allocate();
                for (var child : entry.children()) {
                    // children need a separate statement
                    if (child instanceof RepositoryExternalEntry) {
                        continue;
                    }

                    var value = resultSet.getObject(child.id());
                    // jdbc cannot cast to char ... we handle this manuel
                    if (value instanceof String && child.clazz().equals(char.class)) {
                        value = ((String) value).charAt(0);
                    }

                    if (child.constants().has(RepositoryConstant.VALUE_RENDERING)) {
                        value = child.constants().constant(RepositoryConstant.VALUE_RENDERING).apply(value);
                    }

                    if (child.constants().has(RepositoryConstant.PRIMARY_KEY)) {
                        constants().constantOrCreate(QueryConstant.PRIMARY_SHORTCUT, new QueryConstant.PrimaryShortCut()).add(child, value);
                    }

                    // modify the original field with a new value
                    var childFiled = child.constants().has(RepositoryConstant.PARAM_FIELD) ? child.constants().constant(RepositoryConstant.PARAM_FIELD) : Reflections.on(child.clazz()).field(child.id());
                    Reflections.on(object).modify(childFiled, value);
                }


                for (var child : entry.children()) {
                    if (child instanceof RepositoryExternalEntry externalEntry) {
                        var findProcess = new HikariFindProcess();

                        for (var primary : entry.primaries()) {
                            findProcess.filters().add(new HikariFilter.SequenceMatchFilter(primary.id(), constants().constant(QueryConstant.PRIMARY_SHORTCUT).value(primary), "="));
                        }

                        var run = findProcess.run(externalEntry, reference);

                        // we must transfer maps key and value to a specific map
                        if (externalEntry instanceof RepositoryMapEntry) {
                            var map = new HashMap<>();

                            //todo fix properties not loaded here
                            for (var pair : ((List<Pair<?, ?>>) run)) {
                                map.put(pair.getKey(), pair.getValue());
                            }

                            Reflections.on(object).modify(child.constants().constant(RepositoryConstant.PARAM_FIELD), map);
                        } else {
                            Reflections.on(object).modify(child.constants().constant(RepositoryConstant.PARAM_FIELD), run);
                        }
                    }
                }

                items.add(object);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return items;
    }
}
