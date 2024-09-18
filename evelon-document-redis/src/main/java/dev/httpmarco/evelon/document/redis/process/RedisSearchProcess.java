package dev.httpmarco.evelon.document.redis.process;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryEntry;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.common.Allocator;
import dev.httpmarco.evelon.common.Reflections;
import dev.httpmarco.evelon.document.redis.RedisFilter;
import dev.httpmarco.evelon.document.redis.RedisProcessReference;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class RedisSearchProcess extends QueryProcess<List<Object>, RedisProcessReference, RedisFilter<Object>> {

    @Override
    public @NotNull List<Object> run(@NotNull RepositoryExternalEntry entry, @NotNull RedisProcessReference reference) {
        var result = new ArrayList<>();


        var pattern = entry.id() + ":*";
        var scanArgs = ScanArgs.Builder.matches(pattern);
        var scanCursor = ScanCursor.INITIAL;
        var values = new ArrayList<String>();

        do {
            var cursor = reference.connection().connection().sync().scan(scanCursor, scanArgs);
            scanCursor = cursor;

            values.addAll(cursor.getKeys());
        } while (!scanCursor.isFinished());


        for (var key : values) {
            Map<String, String> map = reference.connection().connection().sync().hgetall(key);
            var object = Allocator.allocate(entry.clazz());


            for (var child : entry.children()) {
                if (child instanceof RepositoryExternalEntry externalEntry) {

                    continue;
                }
                // modify the original field with a new value
                var childFiled = child.constants().has(RepositoryConstant.PARAM_FIELD) ? child.constants().constant(RepositoryConstant.PARAM_FIELD) : Reflections.field(child.clazz(), child.id());

                Reflections.modify(object, childFiled, detectStringValue(child.clazz(), map.get(child.id())));
            }

            result.add(object);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private Object detectStringValue(Class<?> type, String value) {
        if (type.equals(Integer.class) || type.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return Boolean.parseBoolean(value);
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(char.class)) {
            return value.charAt(0);
        } else if (type.equals(UUID.class)) {
            return UUID.fromString(value);
        } else if (type.isEnum()) {
            return Enum.valueOf((Class<? extends Enum>) type, value);
        }
        return value;
    }
}
