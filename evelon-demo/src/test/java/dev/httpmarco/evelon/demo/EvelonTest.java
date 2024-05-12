package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.demo.models.AbstractModel;
import dev.httpmarco.evelon.demo.models.SimpleCollectionModel;
import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.demo.models.objects.EnumObject;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayName("evelon default query methods")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EvelonTest {

    @Contract(pure = true)
    static @NotNull Stream<Arguments> repositoryProvider() {
        return Stream.of(
                Arguments.of(Repository.build(SimpleModel.class).withLayer(H2Layer.class).build(),
                        new SimpleModel(
                                'c',
                                9,
                                10,
                                UUID.randomUUID(),
                                false,
                                "test street",
                                EnumObject.COOKIE)
                ),
                // test abstract classes
                Arguments.of(Repository.build(AbstractModel.class).withLayer(H2Layer.class).build(),
                        new AbstractModel("test", 1, UUID.randomUUID())
                ),
                // simple collection test with String parameter
                Arguments.of(Repository.build(SimpleCollectionModel.class).withLayer(H2Layer.class).build(),
                        new SimpleCollectionModel(UUID.randomUUID(), 1,
                                List.of("roadblock.*", "follow"),
                                List.of(true, true)
                        )
                )
        );
    }

    @Order(0)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    <T> void create(@NotNull Repository<T> repository, T dummy) {
        repository.query().create(dummy);
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    <T> void count(@NotNull Repository<T> repository) {
        assertEquals(1, repository.query().count());
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    <T> void exists(@NotNull Repository<T> repository) {
        assertTrue(repository.query().exists());
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void sum(@NotNull Repository<?> repository) {
        assertNotSame(-1, repository.query(H2Layer.class).sum("age"));
        assertNotSame(0, repository.query(H2Layer.class).sum("age"));
    }

    @Order(4)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void average(@NotNull Repository<?> repository) {
        assertNotSame(-1, repository.query(H2Layer.class).average("age"));
        assertNotSame(0, repository.query(H2Layer.class).average("age"));
    }

    @Order(5)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void find(@NotNull Repository<?> repository) {
        assertSame(1, repository.query().find().size());
    }

    @Order(6)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void findFirst(@NotNull Repository<?> repository) {
        assertNotNull(repository.query().findFirst());
    }

    @Order(7)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    <T> void update(@NotNull Repository<T> repository, T dummy) {
        assertDoesNotThrow(() -> repository.query().update(dummy));
    }

    @Order(8)
    @ParameterizedTest
    @DisplayName("findFirst - match filter")
    @MethodSource("repositoryProvider")
    <T> void findFirstMatch(@NotNull Repository<T> repository) {
        assertDoesNotThrow(() -> repository.query().match("age", 7).findFirst());
    }

    @Order(18)
    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void delete(@NotNull Repository<?> repository) {
        repository.query().delete();
    }
}
