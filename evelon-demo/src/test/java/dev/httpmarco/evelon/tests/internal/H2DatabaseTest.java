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
        @Order(4)
        void count() {
            var response = REPOSITORY.query().filter().match("username", 1).count();

            assertNotNull(response);
            assertEquals(ResponseType.SUCCESS, response.response());
            assertEquals(1, response.result());
        }

        @Test
        @Disabled
        @Order(5)
        void sum() {

        }

        @Test
        @Disabled
        @Order(6)
        void average() {

        }


        @Test
        @Disabled
        @Order(7)
        void createIfNotExists() {

        }

        @Test
        @Disabled
        @Order(8)
        void upsert() {

        }

        @Test
        @Order(9)
        void update() {
            MODEL.address("Street-1");
            var response = REPOSITORY.query().filter().match("username", 1).update(MODEL);

            assertNotNull(response);
            assertEquals(ResponseType.SUCCESS, response.response());
            assertEquals(1, response.modifiedElements());

        }

        @Test
        @DisplayName("filtering (match)")
        @Order(10)
        void filteringMatch() {
            var response = REPOSITORY.query().filter().match("username", 1).findAll();
            assertEquals(1, response.result().size());
            assertEquals(MODEL, response.result().get(0));
        }

        @Test
        @DisplayName("filtering (none-match)")
        @Order(11)
        void filteringNoneMatch() {
            var response = REPOSITORY.query().filter().noneMatch("address", "Street-1").findAll();

            assertEquals(1, response.result().size());
            assertEquals(FILTER_MODEL, response.result().get(0));
        }

        @Test
        @DisplayName("filtering (like)")
        @Order(12)
        void filteringLike() {
            var response = REPOSITORY.query().filter().like("address", "Street").findAll();
            assertEquals(2, response.result().size());
        }

        @Test
        @DisplayName("filtering (minimum value)")
        @Disabled
        @Order(13)
        void min() {

        }

        @Test
        @DisplayName("filtering (maximum value)")
        @Disabled
        @Order(14)
        void max() {

        }

        @Test
        @DisplayName("filtering (between value)")
        @Disabled
        @Order(15)
        void between() {

        }

        @Test
        @DisplayName("filtering (same date)")
        @Disabled
        @Order(16)
        void sameDate() {

        }

        @Test
        @DisplayName("filtering (between time)")
        @Disabled
        @Order(17)
        void betweenTime() {

        }


        @Test
        @DisplayName("filtering (same time)")
        @Disabled
        @Order(18)
        void sameTime() {

        }

        @Test
        @Disabled
        @Order(19)
        void order() {
        }


        @Test
        @Disabled
        @Order(20)
        void delete() {
        }

        @Test
        @Order(21)
        void deletionAll() {
            var response = REPOSITORY.query().deleteAll();

            assertNotNull(response);
            assertEquals(ResponseType.SUCCESS, response.response());
            assertTrue(response.modifiedElements() > 1);
        }
    }
}
