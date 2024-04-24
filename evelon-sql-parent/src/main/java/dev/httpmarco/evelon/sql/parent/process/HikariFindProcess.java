package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryEntry;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;

@AllArgsConstructor
public final class HikariFindProcess extends QueryProcess<HikariProcessReference> {

    private static final String SELECT_QUERY = "SELECT %s FROM %s;";

    private final int skip = -1;
    private final int limit = -1;

    @Override
    public @NotNull Object run(@NotNull RepositoryExternalEntry entry, HikariProcessReference reference) {
        var items = new ArrayList<>();
        var searchedItems = new ArrayList<String>();

        for (var child : entry.children()) {
            if (child instanceof RepositoryExternalEntry externalEntry) {
                //todo
                throw new RuntimeException("External entries are not allowed in this context");
            }
            searchedItems.add(child.id());
        }

        reference.append(SELECT_QUERY.formatted(String.join(", ", searchedItems), entry.id()), new Object[0], resultSet -> {
            var reflections = Reflections.on(entry.clazz());
            var object = reflections.allocate();

            for (var child : entry.children()) {
                if (child instanceof RepositoryExternalEntry externalEntry) {
                    //todo
                    throw new RuntimeException("External entries are not allowed in this context");
                }

                try {
                    var value = resultSet.getObject(child.id());

                    // jdbc cannot cast to char ... we handle this manuel
                    if(value instanceof String && child.clazz().equals(char.class)) {
                        value = ((String) value).charAt(0);
                    }

                    Reflections.on(object).modify(child.id(), value);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            items.add(object);
        });

        return items;
    }
}
