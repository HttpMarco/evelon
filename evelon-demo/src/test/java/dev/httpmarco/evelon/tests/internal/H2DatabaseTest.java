package dev.httpmarco.evelon.tests.internal;

import dev.httpmarco.evelon.query.response.ResponseType;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import dev.httpmarco.evelon.tests.SimpleObjectModel;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class H2DatabaseTest {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Simple Repository Test")
    class SimpleTest {

        private static Repository<SimpleObjectModel> REPOSITORY;
        private static final SimpleObjectModel MODEL = new SimpleObjectModel(1, 200);

        @Test
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.create(SimpleObjectModel.class).layer(H2Layer.class).build());
        }

        @Test
        @Order(1)
        void creation() {
            var response = REPOSITORY.query().create(MODEL);

            assertNotNull(response);
            assertEquals(ResponseType.SUCCESS, response.response());
            assertEquals(1, response.modifiedElements());
        }

        @Test
        @Order(10)
        void deletion() {
            var response = REPOSITORY.query().deleteAll();

            assertNotNull(response);
            assertEquals(ResponseType.SUCCESS, response.response());
            assertEquals(1, response.modifiedElements());
        }
    }
}
