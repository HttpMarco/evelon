package net.bytemc.evelon.repository;

import java.util.stream.Stream;

public interface Filter {

    String sqlFilter(String id);

    <T> Stream<T> localFilter(Stream<T> stream);

}
