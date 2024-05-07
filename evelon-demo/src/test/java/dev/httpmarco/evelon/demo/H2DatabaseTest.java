package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.demo.models.ComplexCollectionModel;
import dev.httpmarco.evelon.demo.models.SimpleCollectionModel;
import dev.httpmarco.evelon.demo.models.SimpleMapModel;
import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.demo.models.objects.EnumObject;
import dev.httpmarco.evelon.demo.models.objects.TestObject1;
import dev.httpmarco.evelon.demo.models.objects.TestObject2;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import org.jetbrains.annotations.Async;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public final class H2DatabaseTest {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Object - Simple repository Test")
    class SimpleModelTest {

        private static Repository<SimpleModel> REPOSITORY;
        private static final SimpleModel DUMMY = new SimpleModel('a', 8, 2000, false, EnumObject.RED);

        @Test
        @Async.Schedule
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.build(SimpleModel.class).withId("persons").withLayer(H2Layer.class).build());
        }

        @Test
        @Order(1)
        void create() {
            REPOSITORY.query().create(DUMMY);
        }

        @Test
        @Order(2)
        void count() {
            assertEquals(1, REPOSITORY.query(H2Layer.class).count());
        }

        @Test
        @Order(3)
        void exists() {
            assertTrue(REPOSITORY.query().exists());
        }

        @Test
        @Order(4)
        void find() {
            var values = REPOSITORY.query(H2Layer.class).find();

            assertNotNull(values);
            assertEquals(1, values.size());

            var model = values.get(0);
            assertNotNull(model);
            assertEquals(8, model.age());
        }

        @Test
        @Order(5)
        void findFirst() {
            var value = REPOSITORY.query().findFirst();

            assertNotNull(value);
            assertEquals(8, value.age());
        }

        @Test
        @DisplayName("findFirst - match filter")
        @Order(6)
        void matchFilter() {
            var value = REPOSITORY.query().match("age", 7).findFirst();
            assertNull(value);
        }

        @Test
        @Order(7)
        void update() {
            REPOSITORY.query().update(new SimpleModel('a', 7, 2000, false, EnumObject.COOKIE));
            var value = REPOSITORY.query(H2Layer.class).findFirst();

            assertNotNull(value);
            assertEquals(7, value.age());
        }

        @Test
        @Order(20)
        void delete() {
            REPOSITORY.query().delete();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Collection - Simple repository Test")
    public class SimpleCollectionModelText {

        private static Repository<SimpleCollectionModel> REPOSITORY;
        private static final SimpleCollectionModel DUMMY = new SimpleCollectionModel(UUID.randomUUID(), List.of("a", "b"), List.of("admin", "test"));

        @Test
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.build(SimpleCollectionModel.class).withId("persons_simple").withLayer(H2Layer.class).build());
        }

        @Test
        @Order(1)
        void create() {
            REPOSITORY.query().create(DUMMY);
        }

        @Test
        @Order(2)
        void count() {
            assertEquals(1, REPOSITORY.query(H2Layer.class).count());
        }

        @Test
        @Order(3)
        void exists() {
            assertTrue(REPOSITORY.query().exists());
        }

        @Test
        @Order(4)
        void find() {
            var values = REPOSITORY.query(H2Layer.class).find();

            assertNotNull(values);
            assertEquals(1, values.size());

            var model = values.get(0);
            assertNotNull(model);
            assertEquals(2, model.getGroups().size());
            assertEquals("test", model.getGroups().get(0));
        }

        @Test
        @Order(4)
        void findFirst() {
            var value = REPOSITORY.query(H2Layer.class).findFirst();

            assertNotNull(value);
            assertEquals(2, value.getGroups().size());
        }

        @Test
        @Order(20)
        void delete() {
            REPOSITORY.query().delete();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Collections - Complex repository Test")
    public class ComplexCollectionModelText {

        private static Repository<ComplexCollectionModel> REPOSITORY;
        private static final ComplexCollectionModel DUMMY = new ComplexCollectionModel(UUID.randomUUID(), List.of(new TestObject1("abc", 2000), new TestObject1("abc", 2000)), List.of(new TestObject2("admin", -1)));

        @Test
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.build(ComplexCollectionModel.class).withId("persons_complex").withLayer(H2Layer.class).build());
        }

        @Test
        @Order(1)
        void create() {
            REPOSITORY.query().create(DUMMY);
        }

        @Test
        @Order(2)
        void count() {
            assertEquals(1, REPOSITORY.query(H2Layer.class).count());
        }

        @Test
        @Order(3)
        void exists() {
            assertTrue(REPOSITORY.query().exists());
        }

        @Test
        @Order(4)
        void find() {
            var values = REPOSITORY.query(H2Layer.class).find();

            assertNotNull(values);
            assertEquals(1, values.size());

            var model = values.get(0);
            assertNotNull(model);
            assertEquals(1, model.getGroups().size());
            assertNotNull(model.getGroups().get(0));
            assertEquals(-1, model.getGroups().get(0).getExpire());
        }

        @Test
        @Order(4)
        void findFirst() {
            var value = REPOSITORY.query(H2Layer.class).findFirst();

            assertNotNull(value);
            assertEquals(1, value.getGroups().size());
        }

        @Test
        @Order(20)
        void delete() {
            REPOSITORY.query().delete();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Maps - Simple key/value repository test")
    public class MapSimpleTest {

        private static Repository<SimpleMapModel> REPOSITORY;
        private static final SimpleMapModel DUMMY = new SimpleMapModel(UUID.randomUUID(), "httpmarco", Map.of("auto-update", false, "autostart", true));

        @Test
        public void initialize() {
            assertNotNull(REPOSITORY = Repository.build(SimpleMapModel.class).withId("maps_simple").withLayer(H2Layer.class).build());
        }
    }
}