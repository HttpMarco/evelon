package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.demo.models.ComplexCollectionModel;
import dev.httpmarco.evelon.demo.models.SimpleCollectionModel;
import dev.httpmarco.evelon.demo.models.SimpleMapModel;
import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.demo.models.objects.EnumObject;
import dev.httpmarco.evelon.demo.models.objects.TestObject1;
import dev.httpmarco.evelon.demo.models.objects.TestObject2;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public final class H2DatabaseTest {
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Object - Simple repository Test")
    class SimpleModelTest {
        private static Repository<SimpleModel> REPOSITORY;
        @Test
        @Order(4)
        void find() {
            var values = REPOSITORY.query(H2Layer.class).find();

            assertNotNull(values);
            assertEquals(2, values.size());

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
        @DisplayName("findFirst - none match filter")
        @Order(7)
        void noneMatchFilter() {
            var value = REPOSITORY.query().noneMatch("age", 10).findFirst();
            assertNotNull(value);
        }

        @Test
        @DisplayName("findFirst - like filter")
        @Order(8)
        void likeFilter() {
            var value = REPOSITORY.query().like("address", "test").findFirst();
            assertNull(value);
        }

        @Test
        @Order(9)
        void update() {
            REPOSITORY.query().update(new SimpleModel('a', 7, 2000, UUID.randomUUID(), false, "test", EnumObject.COOKIE));
            var value = REPOSITORY.query(H2Layer.class).findFirst();

            assertNotNull(value);
            assertEquals(7, value.age());
        }

        @Test
        @Order(10)
        @DisplayName("ordering - ascending")
        void orderAscending() {
            var values = REPOSITORY.query().order("money", Ordering.ASCENDING);
            assertFalse(values.isEmpty());
        }

        @Test
        @Order(11)
        @DisplayName("ordering - descending")
        void orderDescending() {
            var values = REPOSITORY.query().order("money", Ordering.DESCENDING);

            assertFalse(values.isEmpty());
        }
    }
}