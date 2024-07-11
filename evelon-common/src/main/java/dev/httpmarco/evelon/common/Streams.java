package dev.httpmarco.evelon.common;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@UtilityClass
public final class Streams {

    @SuppressWarnings("unchecked")
    public static <T> @NotNull Stream<T> reverse(@NotNull Stream<T> input) {
        Object[] temp = input.toArray();
        return (Stream<T>) IntStream.range(0, temp.length).mapToObj(i -> temp[temp.length - i - 1]);
    }
}
