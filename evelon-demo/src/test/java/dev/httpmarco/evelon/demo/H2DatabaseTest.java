package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.demo.models.SimpleModel;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.h2.H2Layer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public final class H2DatabaseTest {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Simple Model Repository Test")
    class SimpleModelTest {

        private static Repository<SimpleModel> REPOSITORY;

        @Test
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = Repository.build(SimpleModel.class).withId("persons").withLayer(H2Layer.class).build());
        }
    }
}