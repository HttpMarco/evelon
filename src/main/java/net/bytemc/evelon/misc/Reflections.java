package net.bytemc.evelon.misc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public final class Reflections {

    public static final Unsafe unsafe;
    public static final String EMPTY_STRING = "";

    // all java elements
    public static final List<Class<?>> JAVA_ELEMENTS = List.of(String.class, Integer.class, Boolean.class, Short.class, Float.class, Byte.class, Double.class, Long.class);

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get((Object)null);
        } catch (IllegalAccessException | NoSuchFieldException var1) {
            throw new RuntimeException(var1);
        }
    }

    /**
     * @param parent object which contains the field
     * @param field which should be read
     * @return the value of the field
     */
    public static Object readField(Object parent, Field field) {
        field.setAccessible(true);
        try {
            return field.get(parent);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeField(Object parent, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(parent, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param clazz which can maybe a number or something else
     * @return true if the clazz is assignable from number of equals int. Class
     */
    public static boolean isNumber(Class<?> clazz) {
        return clazz.isAssignableFrom(Number.class) || clazz.equals(int.class);
    }


    /**
     * @param tClass which should be allocated
     * @param <T> type of the class
     * @return a new instance of the class
     */
    public static <T> T allocate(Class<T> tClass) {
        try {
            return (T) unsafe.allocateInstance(tClass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> List<T> writeList(List<T> element, T value) {
        element.add(value);
        return element;
    }

    public static Class<?>[] readGenericFromClass(Field field) {
        final ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        return Arrays.stream(parameterizedType.getActualTypeArguments()).map(type -> (Class<?>) type).toArray(value -> new Class<?>[value]);
    }
}
