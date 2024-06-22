package dev.httpmarco.evelon.transformers;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class RecordTransformer implements Transformer {

    // todo: Optional use of unsafe
    private static final Unsafe unsafe;

    static {
        try {
            var unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void manipulateField(Object value, Field field, Object parent) {
        setFieldValue(parent, field, value);
    }


    // todo: wierd shit method, but works for the first time.
    public static void setFieldValue(Object instance, Field field, Object value) {
        try {
            var theInternalUnsafeField = Unsafe.class.getDeclaredField("theInternalUnsafe");
            theInternalUnsafeField.setAccessible(true);
            var theInternalUnsafe = theInternalUnsafeField.get(null);

            var offset = Class.forName("jdk.internal.misc.Unsafe").getMethod("objectFieldOffset", Field.class);
            unsafe.putBoolean(offset, 12, true);

            unsafe.putObject(instance, (long) offset.invoke(theInternalUnsafe, field), value);
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
