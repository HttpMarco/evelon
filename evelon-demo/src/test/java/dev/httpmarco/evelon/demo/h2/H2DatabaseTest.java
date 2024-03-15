package dev.httpmarco.evelon.demo.h2;

import dev.httpmarco.evelon.common.query.response.ResponseType;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryBuilder;
import dev.httpmarco.evelon.demo.virtuals.HierarchyElement;
import dev.httpmarco.evelon.demo.virtuals.HierarchyModel;
import dev.httpmarco.evelon.demo.collections.SimpleCollectionsModel;
import dev.httpmarco.evelon.demo.virtuals.SimpleModel;
import dev.httpmarco.evelon.h2.layer.H2SqlLayer;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class H2DatabaseTest {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Simple Repository Test")
    class SimpleTest {

        private static Repository<SimpleModel> REPOSITORY;
        private static final SimpleModel MODEL = new SimpleModel(1, 200);

        @Test
        @Order(0)
        void initialize() {
            assertNotNull(REPOSITORY = RepositoryBuilder.of(SimpleModel.class).addAfter(H2SqlLayer.class).build());
        }

        @Test
        @Disabled
        @Order(1)
        void creation() {
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().create(MODEL).response());
        }

        @Test
        @Disabled
        @Order(2)
        void exists() {
            assertTrue(REPOSITORY.query().exists());
        }

        @Test
        @Disabled
        @Order(3)
        void get() {
            var model = REPOSITORY.query().findFirst();

            assertNotNull(model);
            assertEquals(MODEL, model);
        }

        @Test
        @Disabled
        @Order(4)
        void filtering() {
            // create another filtering entry
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().create(new SimpleModel(12, 222)).response());

            // match filter
            var filteredModel = REPOSITORY.query().filter().match("username", 1).findFirst();

            assertNotNull(filteredModel);
            assertEquals(MODEL, filteredModel);

            // none match filter todo
            // max filter todo
            // min filter todo

        }

        @Test
        @Disabled
        @Order(5)
        void deleteAll() {
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().deleteAll().response());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Hierarchy Repository Test")
    class HierarchyTest {

        private static Repository<HierarchyModel> REPOSITORY;
        private static final HierarchyModel MODEL = new HierarchyModel("Alex", 200, new HierarchyElement("xyz-1", 220));

        @Test
        @Order(0)
        void initialize() {
            REPOSITORY = RepositoryBuilder.of(HierarchyModel.class).addAfter(H2SqlLayer.class).build();
        }

        @Test
        @Disabled
        @Order(1)
        void creation() {
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().create(MODEL).response());
        }

        @Test
        @Disabled
        @Order(2)
        void exists() {
            assertTrue(REPOSITORY.query().exists());
        }

        @Test
        @Disabled
        @Order(3)
        void get() {
            var model = REPOSITORY.query().findFirst();

            assertNotNull(model);
            assertEquals(MODEL.name(), model.name());
            assertEquals(MODEL.coins(), model.coins());

            // sub object
            assertNotNull(model.children());
            assertEquals(MODEL.children().childrenCount(), model.children().childrenCount());
            assertEquals(MODEL.children().nameId(), model.children().nameId());
        }


        @Test
        @Disabled
        @Order(5)
        void deleteAll() {
            assertEquals(ResponseType.SUCCESS, REPOSITORY.query().deleteAll().response());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("H2 - Simple Collection Repository Test")
    class SimpleCollectionTest {

        private static Repository<SimpleCollectionsModel> REPOSITORY;
        private static final SimpleCollectionsModel MODEL = new SimpleCollectionsModel("8921je89dm", 200, List.of("Alex", "John", "Peter"));


        @Test
        @Order(0)
        void initialize() {
            REPOSITORY = RepositoryBuilder.of(SimpleCollectionsModel.class).addAfter(H2SqlLayer.class).build();
        }
    }
}