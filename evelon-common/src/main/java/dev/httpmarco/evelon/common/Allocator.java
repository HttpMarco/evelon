package dev.httpmarco.evelon.common;

import lombok.experimental.UtilityClass;
import sun.misc.Unsafe;

import java.lang.reflect.InvocationTargetException;

@UtilityClass
public final class Allocator {

    private static final Unsafe unsafe;

    static {
        try {
            var field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (IllegalAccessException | NoSuchFieldException var1) {
            throw new RuntimeException(var1);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T allocate(Class<T> tClass) {
        try {
            return (T) unsafe.allocateInstance(tClass);
        } catch (InstantiationException e) {
            try {
                // default empty constructor
                return tClass.getConstructor().newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException ex) {
                System.err.println("Cannot create new object: " + tClass.getSimpleName());
            }
        }
        return null;
    }
}
