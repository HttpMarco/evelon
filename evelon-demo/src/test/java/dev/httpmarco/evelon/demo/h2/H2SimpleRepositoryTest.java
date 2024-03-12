package dev.httpmarco.evelon.demo.h2;

import dev.httpmarco.evelon.common.query.response.ResponseType;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.demo.HierarchyElement;
import dev.httpmarco.evelon.demo.HierarchyTestRepository;
import dev.httpmarco.evelon.demo.SimpleTestRepository;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class H2SimpleRepositoryTest {

    @Nested
    @DisplayName("H2 - Simple Repository Test")
    class SimpleTest {

        private static Repository<SimpleTestRepository> REPOSITORY;
        private static SimpleTestRepository TEST_MODEL;

        @BeforeAll
        static void initialize() {
            TEST_MODEL = new SimpleTestRepository(1, 200);
            REPOSITORY = RepositoryBuilder.of(SimpleTestRepository.class).addAfter(H2SqlLayer.class).build();
        }

        @Test
        @Order(1)
        void creation() {
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().create(TEST_MODEL).response());
        }

        @Test
        @Order(2)
        void deleteAll() {
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().deleteAll().response());
        }

        @Test
        @Order(3)
        void exists() {
            assertTrue(REPOSITORY.query().exists());
        }

        @Order(4)
        void get() {
            assertEquals(TEST_MODEL, REPOSITORY.query().findFirst());
        }
    }

    @Nested
    @DisplayName("H2 - Hierarchy Repository Test")
    class HierarchyTest {

        private static Repository<HierarchyTestRepository> repository;

        @BeforeAll
        static void initialize() {
            repository = RepositoryBuilder.of(HierarchyTestRepository.class).addAfter(H2SqlLayer.class).build();
        }

        @Test
        void creation() {
            assertEquals(ResponseType.SUCCESS, repository.query().create(new HierarchyTestRepository("Alex", 200, new HierarchyElement("xyz-1", 220))).response());
        }

        @Test
        void deleteAll() {
            assertEquals(ResponseType.SUCCESS, repository.query().deleteAll().response());
        }
    }
}
