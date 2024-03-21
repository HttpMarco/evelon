package dev.httpmarco.evelon.tests.internal;

import dev.httpmarco.evelon.query.response.ResponseType;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import dev.httpmarco.evelon.tests.SimpleObjectModel;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public final class H2DatabaseTest {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Simple Repository Test")
    class SimpleTest {

        private static Repository<SimpleObjectModel> REPOSITORY;
        private static final SimpleObjectModel MODEL = new SimpleObjectModel(1, "Street2");
        private static final SimpleObjectModel FILTER_MODEL = new SimpleObjectModel(2, "Street1");

        @Test
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.create(SimpleObjectModel.class).layer(H2Layer.class).build());
        }

        @Test
        @Order(1)
        void creation() {
            var response = REPOSITORY.query().create(MODEL);
            var response2 = REPOSITORY.query().create(FILTER_MODEL);

            assertNotNull(response);
            assertNotNull(response2);

            assertEquals(ResponseType.SUCCESS, response.response());
            assertEquals(1, response.modifiedElements());

            assertEquals(ResponseType.SUCCESS, response2.response());
            assertEquals(1, response2.modifiedElements());
        }

        @Test
        @Order(2)
        void construct() {
            var response = REPOSITORY.query().findAll();

            assertNotNull(response);
            assertEquals(ResponseType.SUCCESS, response.response());
            assertEquals(2, response.result().size());
            assertEquals(MODEL, response.result().get(0));
        }

        @Test
        @Order(3)
        void exists() {
            var response = REPOSITORY.query().exists();
            assertTrue(response);
        }

        @Test
        @DisplayName("filtering (match)")
        @Order(4)
        void filteringMatch() {
            var response = REPOSITORY.query().filter().match("username", 1).findAll();
            assertEquals(1, response.result().size());
            assertEquals(MODEL, response.result().get(0));
        }

        @Test
        @DisplayName("filtering (none-match)")
        @Order(5)
        void filteringNoneMatch() {
            var response = REPOSITORY.query().filter().noneMatch("address", "Street2").findAll();

            assertEquals(1, response.result().size());
            assertEquals(FILTER_MODEL, response.result().get(0));
        }

        @Test
        @DisplayName("filtering (like)")
        @Order(6)
        void filteringLike() {
            var response = REPOSITORY.query().filter().like("address", "Street").findAll();
            assertEquals(2, response.result().size());
        }

        @Test
        @Order(10)
        void deletion() {
            var response = REPOSITORY.query().deleteAll();

            assertNotNull(response);
            assertEquals(ResponseType.SUCCESS, response.response());
            assertTrue(response.modifiedElements() > 1);
        }
    }
}
