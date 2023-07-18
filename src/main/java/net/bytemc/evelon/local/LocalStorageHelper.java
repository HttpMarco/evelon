package net.bytemc.evelon.local;

import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Filter;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public final class LocalStorageHelper {

    public static <T> Stream<T> filter(List<Filter> filters, Stream<T> defaultStream) {

        for (var filter : filters) {
            defaultStream = filter.localFilter(defaultStream);
        }

        return defaultStream;
    }

    /**
     * @param clazz
     * @param id
     * @param parent
     * @return
     */
    public static @Nullable Number getNumberFilter(Class<?> clazz, String id, Object parent) {
        var element = getObjectFilter(clazz, id, parent);
        if (!Reflections.isNumber(element.getClass())) {
            System.err.println("Error while filtering: The field " + id + " is not a number.");
            return null;
        }
        var number = (Number) element;
        return number;
    }

    /**
     * @param clazz
     * @param id
     * @param parent
     * @return
     */
    public static @Nullable Object getObjectFilter(Class<?> clazz, String id, Object parent) {
        try {
            var fitlerField = clazz.getDeclaredField(id);
            var object = Reflections.readField(parent, fitlerField);
            return object;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}
