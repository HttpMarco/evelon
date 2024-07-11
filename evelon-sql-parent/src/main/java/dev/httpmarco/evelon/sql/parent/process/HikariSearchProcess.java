package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryEntry;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.external.RepositoryCollectionEntry;
import dev.httpmarco.evelon.external.RepositoryMapEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.query.QueryConstant;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.osgan.reflections.Reflections;
import dev.httpmarco.osgan.utils.data.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class HikariSearchProcess extends QueryProcess<List<Object>, HikariProcessReference, HikariFilter<Object>> {

    private static final String SELECT_QUERY = "SELECT %s FROM %s";

    @Override
    public List<Object> run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        var items = entry.children().stream().filter(it -> !it.isExternal()).map(RepositoryEntry::id).toList();
        var query = HikariFilterUtil.appendFiltering(SELECT_QUERY.formatted(String.join(", ", items), entry.id()), filters());

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

        return reference.directly(query, filterArguments(), data -> {
            var result = new ArrayList<>();


            while (data.next()) {
                if (entry instanceof RepositoryCollectionEntry collectionEntry && !(collectionEntry.typeEntry() instanceof RepositoryExternalEntry)) {
                    result.add(renderSingleValue(collectionEntry.typeEntry(), data.getObject(collectionEntry.typeEntry().id())));
                    return result;
                }

                if (entry instanceof RepositoryMapEntry mapEntry && !(mapEntry.keyEntry() instanceof RepositoryExternalEntry) && !(mapEntry.valueEntry() instanceof RepositoryExternalEntry)) {
                    result.add(new Pair<>(data.getObject(mapEntry.keyEntry().id()), data.getObject(mapEntry.valueEntry().id())));
                    return result;
                }

                var reflections = Reflections.on(entry.clazz());

                // we must use the value type of collection entry to create the object
                if (entry instanceof RepositoryCollectionEntry collectionEntry) {
                    reflections = Reflections.on(collectionEntry.typeEntry().clazz());
                }

                Object object = reflections.allocate();

                // only simple fields, because external needs the primary key value
                entry.children().stream().filter(it -> !it.isExternal()).forEach(child -> {
                    try {
                        var value = data.getObject(child.id());

                        // jdbc cannot cast to char ... we handle this manuel
                        if (value instanceof String && child.clazz().equals(char.class)) {
                            value = ((String) value).charAt(0);
                        }

                        value = this.renderSingleValue(child, value);

                        if (child.constants().has(RepositoryConstant.PRIMARY_KEY)) {
                            constants().constantOrCreate(QueryConstant.PRIMARY_SHORTCUT, new QueryConstant.PrimaryShortCut()).add(child, value);
                        }

                        // modify the original field with a new value
                        var childFiled = child.constants().has(RepositoryConstant.PARAM_FIELD) ? child.constants().constant(RepositoryConstant.PARAM_FIELD) : Reflections.on(child.clazz()).field(child.id());

                        if (child.constants().has(RepositoryConstant.TRANSFORMER)) {
                            child.constants().constant(RepositoryConstant.TRANSFORMER).manipulateField(value, childFiled, object);
                        } else {
                            Reflections.on(object).modify(childFiled, value);
                        }
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                });


                entry.children().stream().filter(RepositoryEntry::isExternal).map(it -> (RepositoryExternalEntry) it).forEach(child -> {
                    var findProcess = new HikariSearchProcess();

                    for (var primary : entry.primaries()) {
                        findProcess.filters().add(new HikariFilter.SequenceMatchFilter(primary.id(), constants().constant(QueryConstant.PRIMARY_SHORTCUT).value(primary), "="));
                    }

                    var searchElements = findProcess.run(child, reference);

                    if (child instanceof RepositoryMapEntry) {
                        var map = new HashMap<>();

                        //todo fix properties not loaded here -> maybe already work -> testing
                        searchElements.stream().map(it -> (Pair<?, ?>) it).forEach(pair -> map.put(pair.getKey(), pair.getValue()));
                        Reflections.on(object).modify(child.constants().constant(RepositoryConstant.PARAM_FIELD), map);
                    } else if (child instanceof RepositoryCollectionEntry) {
                        Reflections.on(object).modify(child.constants().constant(RepositoryConstant.PARAM_FIELD), searchElements);
                    } else {
                        Reflections.on(object).modify(child.constants().constant(RepositoryConstant.PARAM_FIELD), searchElements.get(0));
                    }
                });
                result.add(object);
            }
            return result;
        });
    }

    private Object renderSingleValue(@NotNull RepositoryEntry entry, Object untouchedValue) {
        if (entry.constants().has(RepositoryConstant.VALUE_RENDERING)) {
            return entry.constants().constant(RepositoryConstant.VALUE_RENDERING).apply(untouchedValue);
        }
        return untouchedValue;
    }
}
