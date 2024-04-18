package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.demo.models.ComplexListModel;
import dev.httpmarco.evelon.demo.models.SimpleListModel;
import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import org.jetbrains.annotations.Async;
import org.junit.jupiter.api.*;

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
        @Order(20)
        void delete() {
            REPOSITORY.query().delete();
        }
    }
}