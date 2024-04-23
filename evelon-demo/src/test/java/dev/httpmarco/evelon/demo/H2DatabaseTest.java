package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.demo.models.ComplexListModel;
import dev.httpmarco.evelon.demo.models.SimpleListModel;
import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.demo.models.objects.TestObject1;
import dev.httpmarco.evelon.demo.models.objects.TestObject2;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import org.jetbrains.annotations.Async;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public final class H2DatabaseTest {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Simple Model Repository Test")
    class SimpleModelTest {

        private static Repository<SimpleModel> REPOSITORY;

        @Test
        @Async.Schedule
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.build(SimpleModel.class).withId("persons").withLayer(H2Layer.class).build());
        }

        @Test
        @Order(1)
        void create() {
            REPOSITORY.query().create(new SimpleModel('a', 8, 2000, true));
        }


        @Test
        @Order(2)
        void find() {
            var values = REPOSITORY.query(H2Layer.class).find();

            assertNotNull(values);
            assertEquals(1, values.size());

            var model = values.get(0);
            assertNotNull(model);
            assertEquals(model.age(), 8);
        }

        @Test
        @Order(20)
        void delete() {
            REPOSITORY.query().delete();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Simple List Model Repository Test")
    public class SimpleListModelText {

        private static Repository<SimpleListModel> REPOSITORY;

        @Test
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.build(SimpleListModel.class).withId("persons_simple").withLayer(H2Layer.class).build());
        }

        @Test
        @Order(1)
        void create() {
            REPOSITORY.query().create(new SimpleListModel(UUID.randomUUID(), List.of("a", "b"), List.of("admin", "test")));
        }

        @Test
        @Order(20)
        void delete() {
            REPOSITORY.query().delete();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Complex List Model Repository Test")
    public class ComplexListModelText {

        private static Repository<ComplexListModel> REPOSITORY;

        @Test
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.build(ComplexListModel.class).withId("persons_complex").withLayer(H2Layer.class).build());
        }

        @Test
        @Order(1)
        void create() {
            REPOSITORY.query().create(new ComplexListModel(UUID.randomUUID(), List.of(new TestObject1("abc", 2000), new TestObject1("abc", 2000)), List.of(new TestObject2("admin", -1))));
        }

        @Test
        @Order(2)
        void find() {

        }

        @Test
        @Order(20)
        void delete() {
            REPOSITORY.query().delete();
        }
    }
}