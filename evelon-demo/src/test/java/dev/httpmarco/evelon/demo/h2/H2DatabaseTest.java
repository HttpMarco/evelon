package dev.httpmarco.evelon.demo.h2;

import dev.httpmarco.evelon.common.query.response.ResponseType;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.demo.HierarchyElement;
import dev.httpmarco.evelon.demo.HierarchyTestRepository;
import dev.httpmarco.evelon.demo.SimpleModel;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public final class H2DatabaseTest {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Simple Repository Test")
    class SimpleTest {

        private static Repository<SimpleModel> REPOSITORY;
        private static SimpleModel TEST_MODEL;

        @BeforeAll
        static void initialize() {
            TEST_MODEL = new SimpleModel(1, 200);
            REPOSITORY = RepositoryBuilder.of(SimpleModel.class).addAfter(H2SqlLayer.class).build();
        }

        @Test
        @Order(1)
        void creation() {
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().create(TEST_MODEL).response());
        }

        @Test
        @Order(2)
        void exists() {
            assertTrue(REPOSITORY.query().exists());
        }

        @Test
        @Order(3)
        void get() {
            var model = REPOSITORY.query().findFirst();

            assertNotNull(model);
            assertEquals(TEST_MODEL.username(), model.username());
            assertEquals(TEST_MODEL.money(), model.money());
        }

        @Test
        @Order(4)
        void deleteAll() {
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().deleteAll().response());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Hierarchy Repository Test")
    class HierarchyTest {

        private static Repository<HierarchyTestRepository> repository;

        @BeforeAll
        static void initialize() {
            repository = RepositoryBuilder.of(HierarchyTestRepository.class).addAfter(H2SqlLayer.class).build();
        }

        @Test
        @Order(1)
        void creation() {
            assertEquals(ResponseType.SUCCESS, repository.query().create(new HierarchyTestRepository("Alex", 200, new HierarchyElement("xyz-1", 220))).response());
        }

        @Test
        @Order(5)
        void deleteAll() {
            assertEquals(ResponseType.SUCCESS, repository.query().deleteAll().response());
        }
    }
}
