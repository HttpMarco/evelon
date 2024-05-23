package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.demo.models.objects.EnumObject;
import dev.httpmarco.evelon.json.redis.RedisLayer;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

public final class RedisTest {

    @Contract(pure = true)
    static @NotNull Stream<Arguments> repositoryProvider() {
        return Stream.of(
                Arguments.of(Repository.build(SimpleModel.class).withLayer(RedisLayer.class).build(),
                        new SimpleModel(
                                'c',
                                9,
                                10,
                                UUID.randomUUID(),
                                false,
                                "test street",
                                EnumObject.COOKIE)
                ));
    }

    @Order(0)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    <T> void create(@NotNull Repository<T> repository, T dummy) {
        repository.query().create(dummy);
    }
}